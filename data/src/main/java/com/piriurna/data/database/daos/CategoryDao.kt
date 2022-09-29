package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.models.CategoryStats
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM CATEGORIES")
    suspend fun deleteCategories()


    @Query(
        """
            SELECT categoryId, name, completionRate, 
                    substr(name, 0, instr(name,':')) as subTitle, 
                    substr(name, instr(name, ':') + 1, length(name)) as title
            FROM CATEGORIES as table_categories
            LEFT JOIN (
                SELECT ownerCategoryId, COUNT(chosenAnswerId) , COUNT(questionId) , (COUNT(chosenAnswerId)  * 100  / COUNT(questionId)) as completionRate
                FROM QUESTION  
                GROUP BY ownerCategoryId
            ) as table_completion
            ON table_categories.categoryId = table_completion.ownerCategoryId;
    """
    )
    fun getCategories(): Flow<List<CategoryStats>>

}
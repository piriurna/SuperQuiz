package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.CategoryEntity


@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("DELETE FROM CATEGORIES")
    suspend fun deleteCategories()

    @Query("SELECT * FROM CATEGORIES")
    suspend fun getCategories(): List<CategoryEntity>?


}
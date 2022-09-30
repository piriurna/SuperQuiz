package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.models.CategoryStats
import com.piriurna.data.database.models.QuestionWithAnswers
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
              SELECT tbl_categories.*, 
                     completionRate, 
                     substr(name, 0, instr(name,':')) as subTitle, 
                     substr(name, instr(name, ':')+1, length(name)) as title,
                     numberOfQuestions, numberOfCorrectAnswers, numberOWrongAnswers,
                     (numberOfQuestions - numberOfCorrectAnswers - numberOWrongAnswers) as numberOfNotAnsweredQuestions
              FROM categories as tbl_categories
              LEFT JOIN (      
                    SELECT  tbl_question.ownerCategoryId,  
                            (COUNT(chosenAnswerId)  * 100  / COUNT(questionId)) as completionRate,
                            COUNT(questionId) numberOfQuestions, 
                            COUNT(tbl_answers_correct.isCorrectAnswer) numberOfCorrectAnswers, 
                            COUNT(tbl_answers_wrong.isCorrectAnswer) numberOWrongAnswers
                    FROM question as tbl_question
                    LEFT JOIN (
                        
                        SELECT answerId, isCorrectAnswer
                        FROM answer
                        WHERE isCorrectAnswer = 1
                    ) as tbl_answers_correct
                    ON tbl_question.chosenAnswerId == tbl_answers_correct.answerId
                    
                    LEFT JOIN (
                        SELECT answerId, isCorrectAnswer
                        FROM answer
                        WHERE isCorrectAnswer = 0
                    ) as tbl_answers_wrong
                    ON tbl_question.chosenAnswerId == tbl_answers_wrong.answerId
                    GROUP BY tbl_question.ownerCategoryId
                        
              ) as tbl_questions_info
              ON tbl_categories.categoryId = tbl_questions_info.ownerCategoryId;
    """
    )
    fun getCategories(): Flow<List<CategoryStats>>

    @Query("SELECT Count(categoryId) FROM CATEGORIES")
    suspend fun getNUmberOfCategories(): Int
}
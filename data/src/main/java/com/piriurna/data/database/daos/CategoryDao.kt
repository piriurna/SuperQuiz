package com.piriurna.data.database.daos

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
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
              SELECT tbl_categories.*, 
                     completionRate, 
                     substr(name, 0, instr(name,':')) as subTitle, 
                     trim(substr(name, instr(name, ':') + 1, length(name)) , ' ') as title,
                     numberOfQuestions, numberOfCorrectAnswers, numberOWrongAnswers,
                     (numberOfQuestions - numberOfCorrectAnswers - numberOWrongAnswers) as numberOfNotAnsweredQuestions
              FROM categories as tbl_categories
              LEFT JOIN (      
                    SELECT  tbl_question.ownerCategoryId,  
                            (COUNT(chosenAnswerId)  * 100  / COUNT(questionId)) as completionRate,
                            COUNT(questionId) as numberOfQuestions, 
                            COUNT(tbl_answers_correct.isCorrectAnswer) as numberOfCorrectAnswers, 
                            COUNT(tbl_answers_wrong.isCorrectAnswer) as numberOWrongAnswers
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


    @Query(
        """
              SELECT tbl_categories.*, 
                     completionRate, 
                     substr(name, 0, instr(name,':')) as subTitle, 
                     trim(substr(name, instr(name, ':') + 1, length(name)) , ' ') as title,
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
              ON tbl_categories.categoryId = tbl_questions_info.ownerCategoryId
              WHERE tbl_categories.categoryId =:categoryId
    """
    )
    suspend fun getCategory(categoryId: Int): CategoryStats



    @Query("SELECT Count(categoryId) FROM CATEGORIES")
    suspend fun getNumberOfCategories(): Int


    @RawQuery
    suspend fun getMissingCategoriesRawQuery(query: SupportSQLiteQuery): List<Int>

    suspend fun getMissingCategories(values: List<Int>): List<Int> {

        var sb = StringBuilder().append("VALUES ")
        var ids = ""
        for(id in values) {
            ids += "($id),"
        }

        ids = ids.removeSuffix(",")
        sb.append(ids)
        sb.append("EXCEPT SELECT categoryId FROM categories;")
        return getMissingCategoriesRawQuery(SimpleSQLiteQuery(sb.toString()))
    }

}
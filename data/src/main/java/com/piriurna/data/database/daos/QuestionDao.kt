package com.piriurna.data.database.daos

import androidx.room.*
import com.piriurna.data.database.entities.QuestionEntity
import com.piriurna.data.database.models.QuestionWithAnswers
import kotlinx.coroutines.flow.Flow


@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>) : List<Long>

    @Transaction
    @Query("SELECT * FROM QUESTION WHERE ownerCategoryId =:categoryId")
    fun getQuestions(categoryId: Int): Flow<List<QuestionWithAnswers>>

    @Transaction
    @Query("SELECT * FROM QUESTION WHERE questionId IN (:ids)")
    suspend fun getQuestions(ids: List<Long>): List<QuestionWithAnswers>?

    @Transaction
    @Query("SELECT * FROM QUESTION WHERE questionId =:questionId")
    suspend fun getQuestion(questionId: Long): QuestionWithAnswers?

    @Query("UPDATE question SET chosenAnswerId =:chosenAnswerId WHERE questionId =:questionId")
    suspend fun updateQuestion(questionId: Long, chosenAnswerId : Int) : Int

    @Query("DELETE FROM question WHERE ownerCategoryId =:categoryId")
    suspend fun deleteCategoryQuestions(categoryId: Int)
}
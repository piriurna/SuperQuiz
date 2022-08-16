package com.piriurna.data.database.daos

import androidx.room.*
import com.piriurna.data.database.entities.QuestionEntity
import com.piriurna.data.database.models.QuestionWithAnswers

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>) : List<Long>

    @Transaction
    @Query("SELECT * FROM QUESTION WHERE ownerCategoryId =:categoryId")
    suspend fun getQuestions(categoryId: Int): List<QuestionWithAnswers>?

    @Transaction
    @Query("SELECT * FROM QUESTION WHERE questionId IN (:ids)")
    suspend fun getQuestions(ids: List<Long>): List<QuestionWithAnswers>?

    @Update
    suspend fun updateQuestion(questionUpdated : QuestionEntity)
}
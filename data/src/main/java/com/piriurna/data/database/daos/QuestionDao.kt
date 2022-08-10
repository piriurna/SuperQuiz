package com.piriurna.data.database.daos

import androidx.room.*
import com.piriurna.data.database.entities.QuestionEntity

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>) : List<Long>

    @Query("SELECT * FROM QUESTION WHERE categoryId =:categoryId")
    suspend fun getQuestions(categoryId: Int): List<QuestionEntity>?

    @Update
    suspend fun updateQuestion(questionUpdated : QuestionEntity)
}
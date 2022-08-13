package com.piriurna.data.database.daos

import androidx.room.*
import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.models.QuestionWithAnswers

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswers(answers: List<AnswerEntity>)

    @Update
    suspend fun updateAnswer(updatedAnswer : AnswerEntity)

//    @Transaction
//    @Query("SELECT * FROM ANSWER")
//    fun getQuestionAnswers(): List<QuestionWithAnswers>
}
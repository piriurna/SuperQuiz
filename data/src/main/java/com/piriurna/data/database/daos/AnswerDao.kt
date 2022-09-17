package com.piriurna.data.database.daos

import androidx.room.*
import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.models.QuestionWithAnswers

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswers(answers: List<AnswerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerEntity)

    @Query("UPDATE answer SET isEnabled =:enabled WHERE answerId =:answerId")
    suspend fun disableAnswer(answerId : Int, enabled: Boolean)



//    @Transaction
//    @Query("SELECT * FROM ANSWER")
//    fun getQuestionAnswers(): List<QuestionWithAnswers>
}
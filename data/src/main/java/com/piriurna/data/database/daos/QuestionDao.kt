package com.piriurna.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piriurna.data.database.entities.QuestionEntity

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Query("SELECT * FROM QUESTION WHERE categoryId =:categoryId")
    suspend fun getQuestions(categoryId: Int): List<QuestionEntity>?
}
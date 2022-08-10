package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.daos.AnswerDao
import com.piriurna.data.database.daos.CategoryDao
import com.piriurna.data.database.daos.QuestionDao
import com.piriurna.data.database.entities.AnswerEntity
import com.piriurna.data.database.entities.CategoryEntity
import com.piriurna.data.database.entities.QuestionEntity

@Database(
    entities = [
        CategoryEntity::class,
        QuestionEntity::class,
        AnswerEntity::class
   ],
    version = 2,
    exportSchema = false
)
abstract class SuperQuizDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun questionDao() : QuestionDao

    abstract fun answerDao() : AnswerDao

}

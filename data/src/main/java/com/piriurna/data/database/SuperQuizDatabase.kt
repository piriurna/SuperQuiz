package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.daos.CategoryDao
import com.piriurna.data.database.entities.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SuperQuizDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

}

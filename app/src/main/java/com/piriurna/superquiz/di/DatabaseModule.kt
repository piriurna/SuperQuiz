package com.piriurna.superquiz.di

import android.content.Context
import androidx.room.Room
import com.piriurna.data.database.SuperQuizDatabase
import com.piriurna.superquiz.DatabaseConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        SuperQuizDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
}


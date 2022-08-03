package com.piriurna.superquiz.di

import com.piriurna.data.database.daos.CategoryDao
import com.piriurna.data.remote.sources.TriviaApiSource
import com.piriurna.data.repositories.TriviaRepositoryImpl
import com.piriurna.domain.repositories.TriviaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideTriviaRepository(dogApiSource: TriviaApiSource): TriviaRepository {
        return TriviaRepositoryImpl(dogApiSource, Any() as CategoryDao)
    }



}
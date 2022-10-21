package com.piriurna.domain.repositories

import com.piriurna.domain.models.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppDataStoreRepository {

    suspend fun saveAppSettings(appSettings: AppSettings)

    suspend fun getAppSettings(): Flow<AppSettings>

    suspend fun setShouldFetchNewCategories(time : Long)
}
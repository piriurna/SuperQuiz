package com.piriurna.domain.repositories

import com.piriurna.domain.models.AppSettings
import kotlinx.coroutines.flow.Flow

interface AppDataStoreRepository {

    suspend fun saveAppSettings(profile: AppSettings)

    suspend fun getAppSettings(): Flow<AppSettings>

}
package com.piriurna.domain.repositories

import com.piriurna.domain.models.AppSettings
import com.piriurna.domain.models.ProfileSettings
import kotlinx.coroutines.flow.Flow

interface ProfileDataStoreRepository {

    suspend fun saveProfileSettings(appSettings: ProfileSettings)

    suspend fun getProfileSettings(): Flow<ProfileSettings>

}
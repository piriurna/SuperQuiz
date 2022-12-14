package com.piriurna.data.repositories

import android.content.Context
import com.piriurna.data.local.util.appSettingsStore
import com.piriurna.data.local.util.profileSettingsStore
import com.piriurna.domain.models.AppSettings
import com.piriurna.domain.models.ProfileSettings
import com.piriurna.domain.repositories.AppDataStoreRepository
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.Flow

class ProfileDataStoreRepositoryImpl(
    private val context: Context
) : ProfileDataStoreRepository {
    override suspend fun saveProfileSettings(appSettings: ProfileSettings) {
        context.profileSettingsStore.updateData {
            it.copy(
                numberOfQuestions = appSettings.numberOfQuestions,
                userName = appSettings.userName
            )
        }
    }


    override suspend fun getProfileSettings(): Flow<ProfileSettings> {
        return context.profileSettingsStore.data
    }


}
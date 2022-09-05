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
    override suspend fun saveAppSettings(appSettings: ProfileSettings) {
        context.profileSettingsStore.updateData {
            it.copy(
                numberOfQuestions = appSettings.numberOfQuestions
            )
        }
    }


    override suspend fun getAppSettings(): Flow<ProfileSettings> {
        return context.profileSettingsStore.data
    }


}
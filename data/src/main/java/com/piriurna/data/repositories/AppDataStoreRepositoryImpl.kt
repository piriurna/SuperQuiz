package com.piriurna.data.repositories

import android.content.Context
import com.piriurna.data.local.util.appSettingsStore
import com.piriurna.domain.models.AppSettings
import com.piriurna.domain.repositories.AppDataStoreRepository
import kotlinx.coroutines.flow.Flow

class AppDataStoreRepositoryImpl(
    private val context: Context
) : AppDataStoreRepository {
    override suspend fun saveAppSettings(appSettings: AppSettings) {
        context.appSettingsStore.updateData {
            it.copy(
                firstInstall = appSettings.firstInstall,
                onboardingComplete = appSettings.onboardingComplete,
                lastUpdatedCategoriesTimestamp = appSettings.lastUpdatedCategoriesTimestamp
            )
        }
    }


    override suspend fun getAppSettings(): Flow<AppSettings> {
        return context.appSettingsStore.data
    }


    override suspend fun setShouldFetchNewCategories(time : Long) {
        context.appSettingsStore.updateData {
            it.copy(
                lastUpdatedCategoriesTimestamp = time
            )
        }
    }


}
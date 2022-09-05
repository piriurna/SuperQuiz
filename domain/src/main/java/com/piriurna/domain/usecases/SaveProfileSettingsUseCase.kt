package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.ProfileSettings
import com.piriurna.domain.repositories.AppDataStoreRepository
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveProfileSettingsUseCase @Inject constructor(
    private val profileDataStoreRepository: ProfileDataStoreRepository,
    private val appDataStoreRepository: AppDataStoreRepository
) {


    operator fun invoke(profileSettings: ProfileSettings) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val appSettings = appDataStoreRepository.getAppSettings().first()
            profileDataStoreRepository.saveProfileSettings(profileSettings)
            appDataStoreRepository.saveAppSettings(appSettings.copy(
                shouldFetchNewCategories = true
            ))
            emit(Resource.Success(true))
        } catch (e : Exception) {
            emit(Resource.Error(message = "Error saving profile settings"))
        }

    }
}
package com.piriurna.domain.usecases.onboarding

import com.piriurna.domain.Resource
import com.piriurna.domain.repositories.AppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompleteOnboardingUseCase @Inject constructor(
    private val appDataStoreRepository: AppDataStoreRepository
){

    operator fun invoke() : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val appSettings = appDataStoreRepository.getAppSettings().first()

        try {
            appDataStoreRepository.saveAppSettings(
                appSettings.copy(
                    onboardingComplete = true
                )
            )
            emit(Resource.Success(true))
        } catch (error : Exception) {
            emit(Resource.Error("Error saving app settings"))
        }
    }
}
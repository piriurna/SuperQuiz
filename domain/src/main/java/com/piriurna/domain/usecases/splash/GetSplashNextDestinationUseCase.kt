package com.piriurna.domain.usecases.splash

import com.piriurna.domain.Resource
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.domain.models.splash.SplashDestination
import com.piriurna.domain.repositories.AppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSplashNextDestinationUseCase @Inject constructor(
    private val appDataStoreRepository: AppDataStoreRepository
) {

    operator fun invoke(triviaType: LoadTriviaType) : Flow<Resource<SplashDestination>> = flow {
        emit(Resource.Loading())

        val appSettings = appDataStoreRepository.getAppSettings().first()
        val isFirstInstall = triviaType == LoadTriviaType.FIRST_INSTALL

        when {
            isFirstInstall || !appSettings.onboardingComplete -> {
                emit(Resource.Success(SplashDestination.ONBOARDING))
            }
            else -> {
                emit(Resource.Success(SplashDestination.HOME))
            }
        }
}
}
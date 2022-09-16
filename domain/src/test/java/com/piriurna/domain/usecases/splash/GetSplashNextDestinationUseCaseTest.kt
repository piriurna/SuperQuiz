package com.piriurna.domain.usecases.splash

import BaseUseCaseTest
import com.piriurna.domain.Resource
import com.piriurna.domain.models.AppSettings
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.domain.models.splash.SplashDestination
import com.piriurna.domain.repositories.AppDataStoreRepository
import com.piriurna.domain.repositories.TriviaRepository
import com.piriurna.domain.usecases.GetCategoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import javax.inject.Inject

class GetSplashNextDestinationUseCaseTest : BaseUseCaseTest(){
    private lateinit var getSplashNextDestinationUseCase: GetSplashNextDestinationUseCase
    private lateinit var appDataStoreRepository: AppDataStoreRepository


    @Before
    fun setUp() {
        appDataStoreRepository = mock()
        getSplashNextDestinationUseCase = GetSplashNextDestinationUseCase(appDataStoreRepository = appDataStoreRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `first install not seen onboarding get onboarding`() = runBlockingTest {

        val appDataStoreflow = flow { emit(AppSettings(firstInstall = true, onboardingComplete = false)) }

        val loadTriviaType = LoadTriviaType.FIRST_INSTALL

        whenever(appDataStoreRepository.getAppSettings()).thenReturn(appDataStoreflow)


        val emissions = getSplashNextDestinationUseCase(loadTriviaType).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val splashDestination = (result.data as? SplashDestination)
        assert(splashDestination == SplashDestination.ONBOARDING)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `NOT first install not seen onboarding get ONBOARDING`() = runBlockingTest {

        val appDataStoreflow = flow { emit(AppSettings(firstInstall = false, onboardingComplete = false)) }

        val loadTriviaType = LoadTriviaType.NO_CATEGORIES_UPDATED

        whenever(appDataStoreRepository.getAppSettings()).thenReturn(appDataStoreflow)


        val emissions = getSplashNextDestinationUseCase(loadTriviaType).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val splashDestination = (result.data as? SplashDestination)
        assert(splashDestination == SplashDestination.ONBOARDING)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `NOT first install HAVE seen onboarding get HOME`() = runBlockingTest {

        val appDataStoreflow = flow { emit(AppSettings(firstInstall = false, onboardingComplete = true)) }

        val loadTriviaType = LoadTriviaType.NO_CATEGORIES_UPDATED

        whenever(appDataStoreRepository.getAppSettings()).thenReturn(appDataStoreflow)


        val emissions = getSplashNextDestinationUseCase(loadTriviaType).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val splashDestination = (result.data as? SplashDestination)
        assert(splashDestination == SplashDestination.HOME)
    }
}
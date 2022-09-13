package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Onboarding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOnboardingPagesUseCase @Inject constructor(){

    operator fun invoke() : Flow<Resource<List<Onboarding>>> = flow {

        emit(Resource.Loading())

        delay(2000)

        emit(Resource.Success(Onboarding.getOnboardingMockList))
    }
}
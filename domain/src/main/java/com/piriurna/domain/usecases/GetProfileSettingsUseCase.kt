package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.ProfileSettings
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetProfileSettingsUseCase@Inject constructor(
    private val profileDataStoreRepository: ProfileDataStoreRepository
) {


    operator fun invoke() : Flow<Resource<ProfileSettings>> = flow {
        emit(Resource.Loading())

        try {
            val profileSettings = profileDataStoreRepository.getProfileSettings().first()
            emit(Resource.Success(profileSettings))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error getting profile settings"))
        }
    }
}
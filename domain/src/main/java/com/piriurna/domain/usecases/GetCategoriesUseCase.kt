package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Category
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke() : Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())

        val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

        categoriesResult.data?.let {
            emit(Resource.Success(it))
        }?: kotlin.run {
            emit(Resource.Error(message = categoriesResult.error.message!!))
        }


    }
}
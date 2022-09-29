package com.piriurna.domain.usecases

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

        val categories_ : List<Category> = triviaRepository.getDbCategories()

        emit(Resource.Success(categories_))
    }
}
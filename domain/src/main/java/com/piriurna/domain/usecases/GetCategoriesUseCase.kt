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

        val categories : List<Category> = triviaRepository.getDbCategories()

        val categoriesWithCompletionRate = categories.map { category ->
            val questions = triviaRepository.getCategoryQuestionsFromDb(category.id)
            val completionRate = (questions.filter { it.isQuestionAnswered() }.size.toFloat() / questions.size.toFloat()) * 100f
            return@map category.copy(
                completionRate = completionRate.toInt()
            )
        }

        emit(Resource.Success(categoriesWithCompletionRate))
    }
}
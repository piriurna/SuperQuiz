package com.piriurna.domain.usecases.chart

import com.piriurna.domain.Resource
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryStatisticsForAllCategoriesUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke() : Flow<Resource<List<CategoryStatistics>>> = flow {
        emit(Resource.Loading())

        val categories = triviaRepository.getDbCategories()

        val listOfStatistics = categories.map { category ->
            val categoryQuestions = triviaRepository.getCategoryQuestionsFromDb(category.id)

            return@map CategoryStatistics(categoryId = category.id, questions = categoryQuestions)
        }

        emit(Resource.Success(listOfStatistics))
    }
}
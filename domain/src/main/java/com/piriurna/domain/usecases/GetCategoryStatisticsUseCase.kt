package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryStatisticsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(categoryId : Int) : Flow<Resource<CategoryStatistics>> = flow {
        emit(Resource.Loading())

        val questions : List<Question> = triviaRepository.getCategoryQuestionsFromDb(categoryId)

        val categoryStatistics = CategoryStatistics(categoryId = categoryId, questions = questions)
        
        emit(Resource.Success(categoryStatistics))
    }
}
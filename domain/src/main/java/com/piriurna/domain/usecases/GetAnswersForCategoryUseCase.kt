package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnswersForCategoryUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(categoryId : Int) : Flow<Resource<List<Answer>>> = flow {
        emit(Resource.Loading())

        val categories : List<Question> = triviaRepository.getCategoryQuestionsFromDb(categoryId)

        val answers = categories.flatMap { it.allAnswers }

        emit(Resource.Success(answers))
    }
}
package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAnswerUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(questionId: Int, chosenAnswer: Answer) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val question : Question? = triviaRepository.getQuestionFromDb(questionId)

        question?.let {
            val newQuestion = it.copy(
                chosenAnswer = chosenAnswer
            )

            val success = triviaRepository.updateQuestion(newQuestion)
            if(success != 0) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Error getting question"))
            }
        }?: kotlin.run {
            emit(Resource.Error("Error getting question"))
        }
    }
}
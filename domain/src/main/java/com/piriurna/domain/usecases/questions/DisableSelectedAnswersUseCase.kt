package com.piriurna.domain.usecases.questions

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DisableSelectedAnswersUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {


    operator fun invoke(question: Question) : Flow<Resource<Question>> = flow {

        emit(Resource.Loading())

        val answersToDisable = mutableListOf<Answer>()

        repeat(2) {
            val possibleAnswersToDisable = question.getIncorrectAnswers().filter { answer -> answersToDisable.none { it.id == answer.id } }

            val disabledAnswer = possibleAnswersToDisable.random().copy(
                isEnabled = false
            )

            answersToDisable.add(disabledAnswer)
        }

        answersToDisable.forEach {
            triviaRepository.disableAnswer(it.id)
        }

        val updatedQuestion = triviaRepository.getQuestionFromDb(question.id)


        emit(Resource.Success(updatedQuestion!!))
    }
}
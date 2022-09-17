package com.piriurna.domain.usecases.questions

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.questions.CategoryQuestions
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DisableSelectedAnswersUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {


    operator fun invoke(question: Question, categoryQuestions: CategoryQuestions) : Flow<Resource<CategoryQuestions>> = flow {

        emit(Resource.Loading())

        val disabledAnswers = mutableListOf<Answer>()

        repeat(2) {
            val enabledAnswers = question.getIncorrectAnswers().filter { answer -> answer.isEnabled && disabledAnswers.none { it.id == answer.id } }

            val disabledAnswer = enabledAnswers.random().copy(
                isEnabled = false
            )

            disabledAnswers.add(disabledAnswer)
        }

        disabledAnswers.forEach {
            triviaRepository.updateAnswer(question.id, it.copy(
                isEnabled = false
            ))
        }

        val updatedQuestion = triviaRepository.getQuestionFromDb(question.id)

        val newList = categoryQuestions.questions.map {
            if(it.id == updatedQuestion?.id) {
                return@map updatedQuestion
            }

            return@map it
        }

        emit(Resource.Success(CategoryQuestions(
            questions = newList,
            numberOfQuestions = categoryQuestions.numberOfQuestions
        )))
    }
}
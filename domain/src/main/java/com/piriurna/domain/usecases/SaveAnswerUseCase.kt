package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.quotes.Quote
import com.piriurna.domain.repositories.QuoteRepository
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAnswerUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(question: Question, chosenAnswer: Answer) : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        with(question){
            val newQuestion = copy(
                chosenAnswer = chosenAnswer
            )

            val success = triviaRepository.updateQuestion(newQuestion)

            if(success != 0) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(ERROR_GETTING_QUESTION))
            }
        }
    }

    companion object{
        const val ERROR_GETTING_QUESTION = "Error getting question"
    }

}
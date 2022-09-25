package com.piriurna.domain.usecases

import com.piriurna.domain.Resource
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDatabaseNotAnsweredCategoryQuestionsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(categoryId : Int) : Flow<Resource<List<Question>>> = flow {
        emit(Resource.Loading())

        val questions : List<Question> = triviaRepository.getCategoryQuestionsFromDb(categoryId)

        if(questions.isNotEmpty()) {
            val notAnsweredQuestions = questions.filterNot { it.isQuestionAnswered() }
            emit(Resource.Success(notAnsweredQuestions))
        } else {
            emit(Resource.Error("No questions found"))
        }
    }
}
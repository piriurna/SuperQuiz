package com.piriurna.domain.usecases.questions

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchQuestionsForCategoryUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository
) {


    operator fun invoke(categoryId: Int) : Flow<Resource<List<Question>>> = flow {
        emit(Resource.Loading())

        val profileSettings = profileDataStoreRepository.getProfileSettings().first()

        val newQuestions : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(categoryId, profileSettings.numberOfQuestions)

        newQuestions.data?.let { questionsList ->
            triviaRepository.deleteCategoryQuestions(categoryId)

            val databaseQuestionsIds = triviaRepository.insertCategoryQuestionsInDb(questionsList)
            
            questionsList.forEachIndexed { index, question ->
                triviaRepository.insertAnswersInDb(question.allAnswers, databaseQuestionsIds[index].toInt())
            }

            val databaseQuestions = triviaRepository.getQuestionsFromIdList(databaseQuestionsIds)

            emit(Resource.Success(databaseQuestions))
        } ?: kotlin.run {
            emit(Resource.Error("Error fetching new questions for category"))
        }
    }
}
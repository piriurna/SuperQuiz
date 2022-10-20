package com.piriurna.domain.usecases.settings

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import com.piriurna.domain.repositories.TriviaRepository
import com.piriurna.domain.usecases.LoadTriviaDataUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteAndFetchNewCategoriesUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository
) {

    operator fun invoke() : Flow<Resource<Boolean>> = flow {

        emit(Resource.Loading())

        val profileSettings = profileDataStoreRepository.getProfileSettings().first()

        var categories = emptyList<Category>()

        val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

        categoriesResult.data?.let { data ->
            data.forEach { category ->
                triviaRepository.deleteCategoryQuestions(category.id)
            }
            triviaRepository.deleteCategories()

            categories = data
        }?: kotlin.run {
            emit(Resource.Error(message = categoriesResult.error.message!!))
            return@flow
        }

        val categoriesQuestions = HashMap<Int, List<Question>>()

        categories.forEach { category ->

            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id, profileSettings.numberOfQuestions)

            questionsResult.data?.let { questionsData ->
                categoriesQuestions.putIfAbsent(category.id, questionsData)
            }
        }

        if(categoriesQuestions.isEmpty()){
            emit(Resource.Error(message = LoadTriviaDataUseCase.UNABLE_TO_LOAD_TRIVIA))
            return@flow
        }

        triviaRepository.insertCategoriesInDb(categories.filter { category -> categoriesQuestions.any { category.id == it.key } })

        categoriesQuestions.values.forEach { questions ->
            insertQuestionAndRespectiveAnswersInDb(questions)
        }

        emit(Resource.Success(true))
    }

    suspend fun insertQuestionAndRespectiveAnswersInDb(questions: List<Question>){
        val questionIds = triviaRepository.insertCategoryQuestionsInDb(questions)

        questions.forEachIndexed { index, question ->
            triviaRepository.insertAnswersInDb(question.allAnswers, questionIds[index].toInt())
        }
    }
}
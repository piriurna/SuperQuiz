package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.*
import com.piriurna.domain.repositories.AppDataStoreRepository
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshCategoriesUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository
) {

    operator fun invoke() : Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        val profileSettings = profileDataStoreRepository.getProfileSettings().first()

        var categories = emptyList<Category>()


        val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

        categoriesResult.data?.let { data ->
            categories = data
        }?: kotlin.run {
            emit(Resource.Error(message = categoriesResult.error.message!!))
            return@flow
        }

        categories = getMissingCategories(categories = categories, missingIds = triviaRepository.getMissingCategories(categories.map { it.id }))

        if(categories.isEmpty()) { //Does not have new categories in service
            emit(Resource.Success(false))
            return@flow
        }

        //Update categories and questions

        val categoriesQuestions = HashMap<Int, List<Question>>()

        categories.forEach { category ->

            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id, profileSettings.numberOfQuestions)

            questionsResult.data?.let { questionsData ->
                categoriesQuestions.putIfAbsent(category.id, questionsData)
            }
        }

        if(categoriesQuestions.isEmpty()){
            emit(Resource.Error(message = UNABLE_TO_LOAD_TRIVIA))
            return@flow
        } else {
            triviaRepository.insertCategoriesInDb(categories.filter { category -> categoriesQuestions.any { category.id == it.key } })

            categoriesQuestions.values.forEach { questions ->
                insertQuestionAndRespectiveAnswersInDb(questions)
            }

            emit(Resource.Success(true))
        }
    }


    private fun getMissingCategories(categories: List<Category>, missingIds: List<Int>): List<Category> {
        return categories.filter { category -> missingIds.any { category.id == it } }
    }

    private suspend fun insertQuestionAndRespectiveAnswersInDb(questions: List<Question>){
        val questionIds = triviaRepository.insertCategoryQuestionsInDb(questions)

        questions.forEachIndexed { index, question ->
            triviaRepository.insertAnswersInDb(question.allAnswers, questionIds[index].toInt())
        }
    }

    companion object{

        const val UNABLE_TO_LOAD_TRIVIA = "Unable to load trivia"

    }
}
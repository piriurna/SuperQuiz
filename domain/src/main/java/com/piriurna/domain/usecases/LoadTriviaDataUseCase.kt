package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadTriviaDataUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke() : Flow<Resource<LoadTriviaType>> = flow {
        emit(Resource.Loading())

        val firstInstall = true
        val shouldFetchNewCategories = true
        if(firstInstall){ //First install user has no data in database
            val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

            categoriesResult.data?.let { data ->
                triviaRepository.insertCategoriesInDb(data)

                data.forEach { category ->
                    val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id)

                    questionsResult.data?.let { questionsData ->
                        val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                            return@map (it.description to it.allAnswers)
                        }.toMap()
                        triviaRepository.insertCategoryQuestionsInDb(questionsData)

                        val questionsFromDb = triviaRepository.getCategoryQuestionsFromDb(questionsData.first().categoryId)

                        answersForQuestions.forEach { (questionText, answers) ->

                            val questionId = questionsFromDb.firstOrNull { it.description == questionText }?.id

                            if(questionId != null) {
                                triviaRepository.insertAnswersInDb(answers, questionId)
                            }
                        }
                    }?: kotlin.run {
                        if(questionsResult.data == null) //TODO: VER COM GUSTAVO, ERRO NOS TESTES SEM O IF, MAS NA APP FUNCIONA SEMPRE...
                            emit(Resource.Error(message = questionsResult.error.message?:"${category.id} error with question result ${questionsResult.data}"))
                    }
                }

                emit(Resource.Success(LoadTriviaType.FIRST_INSTALL))
            }?: kotlin.run {
                emit(Resource.Error(message = categoriesResult.error.message!!))
            }
        } else { // User entering the app for next times, its supposed for them to have data
            if(shouldFetchNewCategories) { // Should go look if the service has new categories that don't exist in the app yet
                val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

                categoriesResult.data?.let { data ->
                    val nonExistingCategories = data.filterNot { apiCategory ->
                        return@filterNot triviaRepository.getDbCategories().any { it.id == apiCategory.id }
                    }

                    if(nonExistingCategories.isEmpty()) { //Does not have new categories in service
                        emit(Resource.Success(LoadTriviaType.NO_CATEGORIES_UPDATED))
                    } else {
                        triviaRepository.insertCategoriesInDb(nonExistingCategories)
                        nonExistingCategories.forEach { category ->
                            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id)

                            questionsResult.data?.let { questionsData ->
                                val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                                    return@map (it.description to it.allAnswers)
                                }.toMap()
                                triviaRepository.insertCategoryQuestionsInDb(questionsData)

                                val questionsFromDb = triviaRepository.getCategoryQuestionsFromDb(questionsData.first().categoryId)

                                answersForQuestions.forEach { (questionText, answers) ->

                                    val questionId = questionsFromDb.firstOrNull { it.description == questionText }?.id

                                    if(questionId != null) {
                                        triviaRepository.insertAnswersInDb(answers, questionId)
                                    }
                                }
                            }?: kotlin.run {
                                if(questionsResult.data == null)
                                    emit(Resource.Error(message = questionsResult.error.message?:"error"))
                            }
                        }
                        emit(Resource.Success(LoadTriviaType.CATEGORIES_UPDATED))
                    }

                }?: kotlin.run {
                    emit(Resource.Error(message = categoriesResult.error.message!!))
                }
            } else {
                if(triviaRepository.getDbCategories().isEmpty()){ //Should not go look for new categories but the app somehow doesn't have any data in the database
                    val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

                    categoriesResult.data?.let { data ->
                        triviaRepository.insertCategoriesInDb(data)

                        data.forEach { category ->
                            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id)

                            questionsResult.data?.let { questionsData ->
                                val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                                    return@map (it.description to it.allAnswers)
                                }.toMap() //TODO: REFACTOR

                                triviaRepository.insertCategoryQuestionsInDb(questionsData)

                                val questionsFromDb = triviaRepository.getCategoryQuestionsFromDb(questionsData.first().categoryId)

                                answersForQuestions.forEach { (questionText, answers) ->

                                    val questionId = questionsFromDb.firstOrNull { it.description == questionText }?.id

                                    if(questionId != null) {
                                        triviaRepository.insertAnswersInDb(answers, questionId)
                                    }
                                }
                            }?: kotlin.run {
                                emit(Resource.Error(message = questionsResult.error.message?:"error"))
                            }
                        }

                        emit(Resource.Success(LoadTriviaType.CATEGORIES_UPDATED))
                    }?: kotlin.run {
                        emit(Resource.Error(message = categoriesResult.error.message!!))
                    }
                } else { // Normal flow, don't need to do anything different
                    emit(Resource.Success(LoadTriviaType.NO_CATEGORIES_UPDATED))
                }
            }
        }
}
}
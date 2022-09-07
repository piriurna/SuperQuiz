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

class LoadTriviaDataUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val appDataStoreRepository: AppDataStoreRepository,
    private val profileDataStoreRepository: ProfileDataStoreRepository
) {

    operator fun invoke() : Flow<Resource<LoadTriviaType>> = flow {
        emit(Resource.Loading())

        val appSettings = appDataStoreRepository.getAppSettings().first()

        val profileSettings = profileDataStoreRepository.getProfileSettings().first()

        if(appSettings.firstInstall){ //First install user has no data in database
            val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

            categoriesResult.data?.let { data ->
                triviaRepository.insertCategoriesInDb(data)

                data.forEach { category ->
                    val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id, profileSettings.numberOfQuestions)

                    questionsResult.data?.let { questionsData ->
                        val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                            return@map (it.description to it.allAnswers)
                        }.toMap() //TODO: REFACTOR HOW TO SAVE ANSWERS FOR QUESTIONS

                        val ids = triviaRepository.insertCategoryQuestionsInDb(questionsData)

                        val questionsFromDb = triviaRepository.getQuestionsFromIdList(ids)

                        answersForQuestions.forEach { (questionText, answers) ->

                            val questionId = questionsFromDb.firstOrNull { it.description == questionText }?.id

                            if(questionId != null) {
                                triviaRepository.insertAnswersInDb(answers, questionId)
                            }
                        }

                    }?: kotlin.run {
                        emit(Resource.Error(message = questionsResult.error.message!!))
                        return@flow
                    }
                }

                appDataStoreRepository.saveAppSettings(appSettings.copy(
                    firstInstall = false
                ))
                emit(Resource.Success(LoadTriviaType.FIRST_INSTALL))
            }?: kotlin.run {
                emit(Resource.Error(message = categoriesResult.error.message!!))
            }
        } else { // User entering the app for next times, its supposed for them to have data
            if(appSettings.shouldFetchNewCategories) { // Should go look if the service has new categories that don't exist in the app yet
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
                            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id, profileSettings.numberOfQuestions)

                            questionsResult.data?.let { questionsData ->
                                val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                                    return@map (it.description to it.allAnswers)
                                }.toMap()
                                val ids = triviaRepository.insertCategoryQuestionsInDb(questionsData)

                                val questionsFromDb = triviaRepository.getQuestionsFromIdList(ids)

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
                    }

                    appDataStoreRepository.saveAppSettings(appSettings.copy(
                        shouldFetchNewCategories = false
                    ))

                }?: kotlin.run {
                    emit(Resource.Error(message = categoriesResult.error.message!!))
                }
            } else {
                if(triviaRepository.getDbCategories().isEmpty()){ //Should not go look for new categories but the app somehow doesn't have any data in the database
                    val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

                    categoriesResult.data?.let { data ->
                        triviaRepository.insertCategoriesInDb(data)

                        data.forEach { category ->
                            val questionsResult : ApiNetworkResponse<List<Question>> = triviaRepository.getCategoryQuestions(category.id, profileSettings.numberOfQuestions)

                            questionsResult.data?.let { questionsData ->
                                val answersForQuestions : Map<String, List<Answer>> = questionsData.map {
                                    return@map (it.description to it.allAnswers)
                                }.toMap() //TODO: REFACTOR

                                val ids = triviaRepository.insertCategoryQuestionsInDb(questionsData)

                                val questionsFromDb = triviaRepository.getQuestionsFromIdList(ids)

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
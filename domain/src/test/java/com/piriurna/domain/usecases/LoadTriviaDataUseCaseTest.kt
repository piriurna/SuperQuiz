package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.*
import com.piriurna.domain.repositories.AppDataStoreRepository
import com.piriurna.domain.repositories.ProfileDataStoreRepository
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class LoadTriviaDataUseCaseTest : BaseUseCaseTest(){

    private lateinit var loadTriviaDataUseCase: LoadTriviaDataUseCase
    private lateinit var triviaRepository: TriviaRepository
    private lateinit var appDataStoreRepository: AppDataStoreRepository
    private lateinit var profileDataStoreRepository: ProfileDataStoreRepository

    @Before
    fun setUp() {
        triviaRepository = mock()
        appDataStoreRepository= mock()
        profileDataStoreRepository = mock()
        loadTriviaDataUseCase = LoadTriviaDataUseCase(triviaRepository = triviaRepository, appDataStoreRepository = appDataStoreRepository, profileDataStoreRepository = profileDataStoreRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `first install fetch from server and save categories and questions`() = runBlockingTest {

        val categoryList = listOf(
            Category(
                id = 9,
                name = "Test Category 1"
            ),
            Category(
                id = 10,
                name = "Test Category 2"
            )
        )

        val questionsFromServer = listOf(
            Question(
                id = 0,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "Two angles are complementary, if the sum of their measures is",
                allAnswers = Answer.getFirstQuestionMockAnswers()
            ),
            Question(
                id = 0,
                categoryId = 10,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
        )

        val questionsFromDb = listOf(
            Question(
                id = 5,
                categoryId = 9,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "Two angles are complementary, if the sum of their measures is",
                allAnswers = Answer.getFirstQuestionMockAnswers()
            ),
            Question(
                id = 6,
                categoryId = 10,
                type = QuestionType.MULTIPLE_CHOICE,
                difficulty = DifficultyType.MEDIUM,
                description = "With which team did Michael Schumacher make his Formula One debut at the 1991 Belgian Grand Prix?",
                allAnswers = Answer.getSecondQuestionMockAnswers()
            ),
        )

        val questionsForFirstCategory = listOf(questionsFromServer[0])

        val questionsForSecondCategory = listOf(questionsFromServer[1])

        val questionsForFirstCategoryIds = questionsForFirstCategory.map { it.id.toLong() }

        val questionsForSecondCategoryIds = questionsForSecondCategory.map { it.id.toLong() }

        val flow = flow { emit(AppSettings(firstInstall = true)) }

        whenever(appDataStoreRepository.getAppSettings()).thenReturn(flow)

        whenever(triviaRepository.getCategories()).thenReturn(
            ApiNetworkResponse(categoryList)
        )

        whenever(triviaRepository.getCategoryQuestions(9, 10)).thenReturn(
            ApiNetworkResponse(data = questionsForFirstCategory)
        )

        whenever(triviaRepository.insertCategoryQuestionsInDb(questionsForFirstCategory)).thenReturn(
            questionsForFirstCategory.map { it.id.toLong() }
        )

        whenever(triviaRepository.getQuestionsFromIdList(questionsForFirstCategoryIds)).thenReturn(
            listOf(questionsFromDb[0])
        )

        whenever(triviaRepository.getCategoryQuestions(10, 10)).thenReturn(
            ApiNetworkResponse(data = questionsForSecondCategory)
        )


        whenever(triviaRepository.insertCategoryQuestionsInDb(questionsForSecondCategory)).thenReturn(
            questionsForSecondCategory.map { it.id.toLong() }
        )

        whenever(triviaRepository.getQuestionsFromIdList(questionsForFirstCategoryIds)).thenReturn(
            listOf(questionsFromDb[1])
        )

        whenever(triviaRepository.getCategoryQuestionsFromDb(9)).thenReturn(
            listOf(
                questionsFromDb[0]
            )
        )

        whenever(triviaRepository.getCategoryQuestionsFromDb(10)).thenReturn(
            listOf(
                questionsFromDb[1]
            )
        )


        // Execute the use-case
        val emissions = loadTriviaDataUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val loadTriviaType = (result.data as? LoadTriviaType)
        assert(loadTriviaType == LoadTriviaType.FIRST_INSTALL)

        verify(triviaRepository, times(1)).insertCategoriesInDb(categoryList)

        verify(triviaRepository, times(1)).insertCategoryQuestionsInDb(listOf(questionsFromServer[0]))

        verify(triviaRepository, times(1)).insertCategoryQuestionsInDb(listOf(questionsFromServer[1]))

//        verify(triviaRepository, times(1)).insertAnswersInDb(questionsFromServer[0].allAnswers, questionsFromDb[0].id)

//        verify(triviaRepository, times(1)).insertAnswersInDb(questionsFromServer[1].allAnswers, questionsFromDb[1].id)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `on fetch new categories no new categories found`() = runBlockingTest {

        val categoriesInDb = listOf(
            Category(
                id = 0,
                name = "Test Category 1"
            ),
            Category(
                id = 1,
                name = "Test Category 2"
            )
        )

        val categoriesInServer = listOf(
            Category(
                id = 0,
                name = "Test Category 1"
            ),
            Category(
                id = 1,
                name = "Test Category 2"
            )
        )

        whenever(triviaRepository.getCategories()).thenReturn(
            ApiNetworkResponse(categoriesInServer)
        )

        whenever(triviaRepository.getDbCategories()).thenReturn(categoriesInDb)

        // Execute the use-case
        val emissions = loadTriviaDataUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val loadTriviaType = (result.data as LoadTriviaType)
        assert(loadTriviaType == LoadTriviaType.NO_CATEGORIES_UPDATED) {
            loadTriviaType
        }

        verify(triviaRepository, times(0)).insertCategoriesInDb(categoriesInServer)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `on fetch new categories new categories found and inserted`() = runBlockingTest {
        val categoriesInDb = listOf(
            Category(
                id = 9,
                name = "Test Category 1"
            ),
            Category(
                id = 10,
                name = "Test Category 2"
            )
        )

        val newCategory = Category(
            id = 11,
            name = "Test Category 3"
        )

        val categoriesInServer = listOf(
            Category(
                id = 9,
                name = "Test Category 1"
            ),
            Category(
                id = 10,
                name = "Test Category 2"
            ),

            newCategory
        )
        whenever(triviaRepository.getDbCategories()).thenReturn(categoriesInDb)

        whenever(triviaRepository.getCategories()).thenReturn(
            ApiNetworkResponse(categoriesInServer)
        )

        whenever(triviaRepository.getCategoryQuestions(newCategory.id, 10)).thenReturn(
            ApiNetworkResponse(data = listOf(
                Question.mockQuestions[2]
            ))
        )

        whenever(triviaRepository.getCategoryQuestionsFromDb(newCategory.id)).thenReturn(
            listOf(
                Question.mockQuestions[2]
            )
        )


        // Execute the use-case
        val emissions = loadTriviaDataUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val loadTriviaType = (result.data as LoadTriviaType)
        assert(loadTriviaType == LoadTriviaType.CATEGORIES_UPDATED)

        verify(triviaRepository, times(1)).insertCategoriesInDb(listOf(newCategory))

        verify(triviaRepository, times(1)).insertCategoryQuestionsInDb(listOf(Question.mockQuestions[2]))

        verify(triviaRepository, times(1)).insertAnswersInDb(Question.mockQuestions[2].allAnswers, Question.mockQuestions[2].id)
    }
}
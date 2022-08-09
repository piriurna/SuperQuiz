package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoadTriviaDataUseCaseTest : BaseUseCaseTest(){

    private lateinit var loadTriviaDataUseCase: LoadTriviaDataUseCase
    private lateinit var triviaRepository: TriviaRepository

    @Before
    fun setUp() {
        triviaRepository = mock()
        loadTriviaDataUseCase = LoadTriviaDataUseCase(triviaRepository = triviaRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `first install fetch from server and save categories and questions`() = runBlockingTest {

        val categoryList = listOf(
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
            ApiNetworkResponse(categoryList)
        )

        // Execute the use-case
        val emissions = loadTriviaDataUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val loadTriviaType = (result.data as LoadTriviaType)
        assert(loadTriviaType == LoadTriviaType.FIRST_INSTALL)

        verify(triviaRepository, times(1)).insertCategoriesInDb(categoryList)
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
        assert(loadTriviaType == LoadTriviaType.NO_CATEGORIES_UPDATED)

        verify(triviaRepository, times(0)).insertCategoriesInDb(categoriesInServer)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `on fetch new categories new categories found and inserted`() = runBlockingTest {
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

        val newCategory = Category(
            id = 2,
            name = "New Category"
        )

        val categoriesInServer = listOf(
            Category(
                id = 0,
                name = "Test Category 1"
            ),
            Category(
                id = 1,
                name = "Test Category 2"
            ),

            newCategory
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
        assert(loadTriviaType == LoadTriviaType.CATEGORIES_UPDATED)

        verify(triviaRepository, times(1)).insertCategoriesInDb(listOf(newCategory))
    }
}
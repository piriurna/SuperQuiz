package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.ApiNetworkError
import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Category
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

class GetCategoriesUseCaseTest : BaseUseCaseTest(){
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    private lateinit var triviaRepository: TriviaRepository


    @Before
    fun setUp() {
        triviaRepository = mock()
        getCategoriesUseCase = GetCategoriesUseCase(triviaRepository = triviaRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {

        val category = Category(
            id = 0,
            name = "Test Category"
        )

        whenever(triviaRepository.getCategories()).thenReturn(
            ApiNetworkResponse(listOf(category))
        )

        // Execute the use-case
        val emissions = getCategoriesUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val resultCategory = (result.data as List<Category>)[0]
        assert(resultCategory.name == category.name)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive error`() = runBlockingTest {
        whenever(triviaRepository.getCategories()).thenReturn(
            ApiNetworkResponse(
                error = ApiNetworkError(message = "server error")
            )
        )

        // Execute the use-case
        val emissions = getCategoriesUseCase().toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        assert(result is Resource.Error)
        assert(result.message == "server error")
        verify(triviaRepository, times(1)).getCategories()
    }
}
package com.piriurna.domain.usecases

import BaseUseCaseTest
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCategoryStatisticsUseCaseTest : BaseUseCaseTest() {

    private lateinit var getCategoryStatisticsUseCase: GetCategoryStatisticsUseCase
    private lateinit var triviaRepository: TriviaRepository

    @Before
    fun setUp() {
        triviaRepository = mock()
        getCategoryStatisticsUseCase = GetCategoryStatisticsUseCase(triviaRepository = triviaRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {

        val category = Category(
            id = 9,
            name = "General Knowledge",
            completionRate = 80,
        )

        whenever(triviaRepository.getCategoryQuestionsFromDb(category.id)).thenReturn(
            listOf(Question.mockQuestions[0])
        )


        // Execute the use-case
        val emissions = getCategoryStatisticsUseCase(category.id).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val categoryStatistics = (result.data as CategoryStatistics)
        assert(categoryStatistics.categoryId == category.id)
    }
}
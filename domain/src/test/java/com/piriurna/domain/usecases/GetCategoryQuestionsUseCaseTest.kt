package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCategoryQuestionsUseCaseTest {

    private lateinit var getCategoryQuestionsUseCase: GetCategoryQuestionsUseCase
    private lateinit var triviaRepository: TriviaRepository


    @Before
    fun setUp() {
        triviaRepository = mock()
        getCategoryQuestionsUseCase = GetCategoryQuestionsUseCase(triviaRepository = triviaRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {
        val category = Category.mockCategoryList[1]

        val questions = Question.mockQuestions.filter { it.categoryId == category.id }

        whenever(triviaRepository.getCategoryQuestionsFromDb(categoryId = category.id)).thenReturn(
            questions
        )

        val emissions = getCategoryQuestionsUseCase(category.id).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val resultQuestions = (result.data as List<Question>)
        assert(resultQuestions.isNotEmpty())
    }
}
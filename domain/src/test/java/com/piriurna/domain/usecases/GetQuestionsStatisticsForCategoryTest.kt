package com.piriurna.domain.usecases

import BaseUseCaseTest
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

class GetQuestionsStatisticsForCategoryTest : BaseUseCaseTest() {

    private lateinit var getQuestionsStatisticsForCategory: GetQuestionsStatisticsForCategory
    private lateinit var triviaRepository: TriviaRepository

    @Before
    fun setUp() {
        triviaRepository = mock()
        getQuestionsStatisticsForCategory = GetQuestionsStatisticsForCategory(triviaRepository = triviaRepository)
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
        val emissions = getQuestionsStatisticsForCategory(category.id).toList()
        var result = (emissions[0] as Resource)

        assert(result is Resource.Loading)

        result = (emissions[1] as Resource)
        val resultAnswers = (result.data as List<Answer>)
        assert(resultAnswers.size == 4)
        assert(resultAnswers[0].isCorrectAnswer)
    }
}
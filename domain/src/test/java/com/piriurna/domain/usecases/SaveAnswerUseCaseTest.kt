package com.piriurna.domain.usecases

import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SaveAnswerUseCaseTest {

    private lateinit var saveAnswerUseCase: SaveAnswerUseCase
    private lateinit var triviaRepository: TriviaRepository


    @Before
    fun setUp() {
        triviaRepository = mock()
        saveAnswerUseCase = SaveAnswerUseCase(triviaRepository = triviaRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `ask for data and receive data`() = runBlockingTest {

        val questions = Question.mockQuestions

        val category = questions[0].categoryId

        val chosenAnswer = Answer.getFirstQuestionMockAnswers()

        whenever(triviaRepository.getCategoryQuestionsFromDb(category)).thenReturn(
            questions
        )

    }
}
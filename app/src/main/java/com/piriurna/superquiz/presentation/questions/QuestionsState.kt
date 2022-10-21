package com.piriurna.superquiz.presentation.questions

import com.piriurna.common.models.SQError
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.quotes.Quote

data class QuestionsState(
    val isLoading : Boolean = true,
    val category : Category = Category.mockCategoryList[0],
    val questionsList: List<Question> = emptyList(),
    val currentQuestion : Question? = null,
    val quotes : List<Quote> = emptyList(),
    val destination : QuestionDestination = QuestionDestination.SHOW_QUESTION,
    val error : SQError? = null
) {

    fun isLastQuestion() = questionsList.size - 1 == getCurrentQuestionIndex()

    fun getCurrentQuestionIndex() = questionsList.indexOfFirst { it.id == currentQuestion?.id }

    fun getNextQuestion() = questionsList[getCurrentQuestionIndex() + 1]

    fun isShowingAnswerResult() = destination == QuestionDestination.SHOW_ANSWER_RESULT

}
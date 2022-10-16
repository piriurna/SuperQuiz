package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.quotes.Quote

data class QuestionsState(
    val isLoading : Boolean = true,
    val category : Category = Category.mockCategoryList[0],
    val questionsList: List<Question> = emptyList(),
    val currentQuestion : Question? = null,
    val quotes : List<Quote> = emptyList(),
    val showingAnswerResult : Boolean = false,
    val destination : QuestionDestination = QuestionDestination.SHOW_QUESTION
) {

    fun isLastQuestion() = questionsList.size - 1 == getCurrentQuestionIndex()

    fun getCurrentQuestionIndex() = questionsList.indexOfFirst { it.id == currentQuestion?.id }

}
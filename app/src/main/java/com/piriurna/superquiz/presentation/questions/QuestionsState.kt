package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Question
import com.piriurna.domain.models.quotes.Quote

data class QuestionsState(
    val isLoading : Boolean = true,
    val categoryQuestions: List<Question> = emptyList(),
    val quotes : List<Quote> = emptyList(),
    val lastAnsweredQuestionId : Int = 0,
    val categoryId : Int = 0,
    val currentUnaswerdQuestion: Question? = null
) {

    fun isLastQuestion(index : Int) = categoryQuestions.size - 1 == index

}
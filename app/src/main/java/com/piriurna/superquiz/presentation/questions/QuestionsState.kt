package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.questions.CategoryQuestions

data class QuestionsState(
    val isLoading : Boolean = false,
    val categoryQuestions: CategoryQuestions = CategoryQuestions()
)
package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.Question

data class QuestionsState(
    val isLoading : Boolean = false,
    val questions: List<Question> = emptyList()
)
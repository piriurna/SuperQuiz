package com.piriurna.superquiz.presentation.questions

import com.piriurna.domain.models.questions.CategoryInformation

data class QuestionsState(
    val isLoading : Boolean = false,
    val categoryInformation: CategoryInformation = CategoryInformation()
)
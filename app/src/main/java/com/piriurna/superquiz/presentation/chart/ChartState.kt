package com.piriurna.superquiz.presentation.chart

import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question

data class ChartState(
    val isLoading: Boolean = false,
    val questions : List<Question> = emptyList(),
    val categories : List<Category> = emptyList()
)
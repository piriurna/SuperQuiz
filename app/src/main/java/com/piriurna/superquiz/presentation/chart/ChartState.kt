package com.piriurna.superquiz.presentation.chart

import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question

data class ChartState(
    val isLoading: Boolean = false,
    var categoryStatisticsList: List<CategoryStatistics> = emptyList()
)
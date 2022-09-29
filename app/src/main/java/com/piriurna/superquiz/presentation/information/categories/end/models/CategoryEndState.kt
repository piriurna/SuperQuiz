package com.piriurna.superquiz.presentation.information.categories.end.models

import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question

data class CategoryEndState(
    val isLoading : Boolean = false,
    val categoryStatistics: CategoryStatistics = CategoryStatistics()
) {
}
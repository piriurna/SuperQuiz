package com.piriurna.superquiz.presentation.information.models

import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question

data class SuccessState(
    val isLoading : Boolean = false,
    val categoryStatistics: CategoryStatistics = CategoryStatistics()
) {
}
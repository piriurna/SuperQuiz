package com.piriurna.superquiz.presentation.information.categories.end.models

import com.piriurna.common.models.SQError
import com.piriurna.domain.models.Category

data class CategoryEndState(
    val isLoading : Boolean = false,
    val category: Category? = null,
    val destination : CategoryEndDestination = CategoryEndDestination.NO_STATE,
    val error : SQError? = null
) {
}
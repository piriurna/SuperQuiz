package com.piriurna.superquiz.presentation.information.categories.end.models

import com.piriurna.superquiz.SQBaseEvent

sealed class CategoryEndEvents : SQBaseEvent() {

    class GetCategoryStatistics(val categoryId : Int) : CategoryEndEvents()
}
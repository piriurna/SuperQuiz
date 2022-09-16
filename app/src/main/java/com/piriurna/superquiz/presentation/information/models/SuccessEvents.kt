package com.piriurna.superquiz.presentation.information.models

import com.piriurna.superquiz.SQBaseEvent

sealed class SuccessEvents : SQBaseEvent() {

    class GetCategoryStatistics(val categoryId : Int) : SuccessEvents()
}
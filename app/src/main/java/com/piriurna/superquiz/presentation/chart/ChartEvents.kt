package com.piriurna.superquiz.presentation.chart

import com.piriurna.superquiz.SQBaseEvent

sealed class ChartEvents : SQBaseEvent(){
    class GetAnswersForCategory(val categoryId: Int) : ChartEvents()

    object GetCategories : ChartEvents()
}

package com.piriurna.superquiz.presentation.chart

import com.piriurna.superquiz.SQBaseEvent

sealed class ChartEvents : SQBaseEvent(){
    object GetCategoryStatistics : ChartEvents()
}

package com.piriurna.common.models

import androidx.compose.ui.graphics.Color
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.primaryGreen
import com.piriurna.common.theme.purple

data class PieChartSection(
    val percentage: Int, //This has to be 0-100%
    val color: Color,
    var label : String = ""
) {

    companion object {

        fun getMockPieChartSections() = listOf(
            PieChartSection(
                percentage = 45,
                color = errorColor,
                label = "Incorrect Answers"
            ),
            PieChartSection(
                percentage = 15,
                color = primaryGreen,
                label = "Incorrect Answers"
            ),
            PieChartSection(
                percentage = 10,
                color = orange
            ),
            PieChartSection(
                percentage = 10,
                color = purple
            )
        )
    }
}
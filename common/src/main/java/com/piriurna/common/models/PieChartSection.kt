package com.piriurna.common.models

import androidx.compose.ui.graphics.Color
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.purple

data class PieChartSection(
    val value: Float,
    val color: Color,
    var selected: Boolean = false
) {

    companion object {

        fun getMockPieChartSections() = listOf(
            PieChartSection(
                value = 10f,
                color = Color.Red
            ),
            PieChartSection(
                value = 15f,
                color = Color.Green
            ),
            PieChartSection(
                value = 10f,
                color = orange
            ),
            PieChartSection(
                value = 10f,
                color = purple
            )
        )
    }
}
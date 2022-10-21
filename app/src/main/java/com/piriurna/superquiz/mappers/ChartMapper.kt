package com.piriurna.superquiz.mappers

import androidx.compose.ui.graphics.Color
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.primaryGreen
import com.piriurna.common.theme.progressBlue
import com.piriurna.common.theme.purple
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question

fun CategoryStatistics.toPieChartSections() : List<PieChartSection> {
    return listOf(
        PieChartSection(
            getPercentageOfCorrectAnswers(),
            Color.Green,
        ),
        PieChartSection(
            getPercentageOfIncorrectAnswers(),
            Color.Red,
        ),
    )
}

fun Category.toPieChartSections() : List<PieChartSection> {
    return listOf(
        PieChartSection(
            percentage = getPercentageOfCorrectAnswers(),
            color = Color(74, 21, 173),
            label = "Correct Answers"
        ),
        PieChartSection(
            percentage = getPercentageOfIncorrectAnswers(),
            color = Color(252, 186, 3),
            label = "Incorrect Answers"
        ),
    )
}

fun Category.getProgressChart(progressColor: Color = purple) : List<PieChartSection> {
    return listOf(
        PieChartSection(
            completionRate,
            progressColor,
        ),
    )
}

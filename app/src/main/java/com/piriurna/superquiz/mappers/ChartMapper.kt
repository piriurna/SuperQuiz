package com.piriurna.superquiz.mappers

import androidx.compose.ui.graphics.Color
import com.piriurna.common.models.PieChartSection
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question

fun CategoryStatistics.toPieChartSections() : List<PieChartSection> {
    return listOf(
        PieChartSection(
            getNotAnsweredQuestions().toFloat(),
            Color.Gray
        ),
        PieChartSection(
            correctAnswers.toFloat(),
            Color.Green,
        ),
        PieChartSection(
            incorrectAnswers.toFloat(),
            Color.Red,
        ),
    )
}
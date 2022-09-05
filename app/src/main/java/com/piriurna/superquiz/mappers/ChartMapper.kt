package com.piriurna.superquiz.mappers

import androidx.compose.ui.graphics.Color
import com.piriurna.common.models.PieChartSection
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question

fun List<Question>.toPieChartSections() : List<PieChartSection> {

    var notAnsweredQuestionsCount = 0

    var correctAnswersCount = 0

    var incorrectAnswersCount = 0

    this.forEach {
        if (it.isQuestionAnswered()) {
            if(it.chosenAnswer?.isCorrectAnswer == true)
                correctAnswersCount++
             else
                incorrectAnswersCount++
        } else {
            notAnsweredQuestionsCount++
        }
    }


    return listOf(
        PieChartSection(
            notAnsweredQuestionsCount.toFloat(),
            Color.Gray
        ),
        PieChartSection(
            correctAnswersCount.toFloat(),
            Color.Green,
        ),
        PieChartSection(
            incorrectAnswersCount.toFloat(),
            Color.Red,
        ),
    )
}
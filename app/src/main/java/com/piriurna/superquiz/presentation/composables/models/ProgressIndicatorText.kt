package com.piriurna.superquiz.presentation.composables.models

import androidx.compose.ui.graphics.Color

sealed class ProgressIndicatorText(
    val percentage : Int, //0-100 percentage
    val text : String,
    val incompleteColor: Color = Color.LightGray,
    val completedColor: Color = Color.Black,
    val progressColor : Color = Color.Black
) {

    class CompletedExplicitText(percentage: Int) : ProgressIndicatorText(
            percentage = percentage,
            text = "You completed ${percentage}%"
        )

    class FractionText(percentage: Int) : ProgressIndicatorText(
        percentage = percentage,
        text = "${(percentage/10)}/10"
    )
}
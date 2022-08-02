package com.piriurna.superquiz.presentation.composables.models

import androidx.compose.ui.graphics.Color
import com.piriurna.superquiz.ui.theme.incompleteGray
import com.piriurna.superquiz.ui.theme.primaryGreen
import com.piriurna.superquiz.ui.theme.progressBlue

data class ProgressIndicatorModel(
    val progress : Int,
    val progressIndicatorText: ProgressIndicatorText? = null,
    val chipModel: ChipModel? = null,
    val progressColor: Color = progressBlue,
    val backgroundColor: Color = incompleteGray,
    val completedColor: Color = primaryGreen
){
    fun isCompleted() : Boolean{
        return progress == 100
    }

    fun isZero() : Boolean {
        return progress == 0
    }
}

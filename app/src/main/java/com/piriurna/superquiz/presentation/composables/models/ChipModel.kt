package com.piriurna.superquiz.presentation.composables.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.piriurna.superquiz.ui.theme.lightOrange
import com.piriurna.superquiz.ui.theme.orange

data class ChipModel(
    val icon: ImageVector,
    val text : String,
    val backgroundColor: Color = lightOrange,
    val foregroundColor: Color = orange
) {
}
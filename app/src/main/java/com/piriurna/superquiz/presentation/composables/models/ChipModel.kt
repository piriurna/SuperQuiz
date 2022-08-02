package com.piriurna.superquiz.presentation.composables.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ChipModel(
    val icon: ImageVector,
    val text : String,
    val backgroundColor: Color,
    val foregroundColor: Color
) {
}
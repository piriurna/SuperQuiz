package com.piriurna.superquiz.presentation.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.superquiz.ui.theme.primaryGreen

@Composable
fun SQRadioButton(
    selected: Boolean,
    enabled: Boolean,
    correct: Boolean? = null,
    onClick : () -> Unit,
    selectedColor : Color,
    correctColor: Color = primaryGreen,
    incorrectColor : Color = Color.Red
) {
    val colorSelection = if(correct != null) {
        if(correct) {
            correctColor
        } else {
            incorrectColor
        }
    } else {
        selectedColor
    }
    RadioButton(
        selected = selected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors()
    )
}

@Preview
@Composable
private fun SQRadioButtonPreview() {

}
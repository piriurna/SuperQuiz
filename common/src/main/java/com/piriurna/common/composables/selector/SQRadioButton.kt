package com.piriurna.common.composables.selector

import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.theme.primaryBlue
import com.piriurna.common.theme.primaryGreen

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
    SQRadioButton(selected = true, enabled = true, onClick = { }, selectedColor = primaryBlue)
}
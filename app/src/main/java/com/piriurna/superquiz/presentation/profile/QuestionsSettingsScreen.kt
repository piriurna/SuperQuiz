package com.piriurna.superquiz.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.text.SQText

@Composable
fun QuestionsSettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SQText(text = "Número de perguntas por categoria")
        var sliderPosition by remember { mutableStateOf(5f) }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 5f..20f,
            steps = 2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsSettingsScreenPreview() {
    QuestionsSettingsScreen()
}
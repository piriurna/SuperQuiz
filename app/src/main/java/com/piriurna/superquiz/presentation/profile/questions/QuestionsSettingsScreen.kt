package com.piriurna.superquiz.presentation.profile.questions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.superquiz.presentation.profile.ProfileSettingsViewModel
import kotlin.math.abs

@Composable
fun QuestionsSettingsScreen() {

    val viewModel : ProfileSettingsViewModel = hiltViewModel()

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SQText(text = "Número de perguntas por categoria")

        val valueRange = 5f..20f
        val availableOptions = listOf(5f, 10f, 15f, 20f)
        var sliderPosition by remember { mutableStateOf(state.profileSettings.numberOfQuestions.toFloat()) }

        Slider(
            value = sliderPosition,
            onValueChange = { value ->
                val closestNumber = availableOptions.minByOrNull { abs(value - it ) }
                sliderPosition = closestNumber?:value
                viewModel.triggerSaveNumberOfQuestions(sliderPosition.toInt())
            },
            valueRange = valueRange,
            steps = 2
        )

        if(sliderPosition in availableOptions)
            SQText(text = sliderPosition.toInt().toString(), style = SQStyle.TextLatoBold)
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionsSettingsScreenPreview() {
    QuestionsSettingsScreen()
}
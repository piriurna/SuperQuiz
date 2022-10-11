package com.piriurna.common.composables.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import kotlin.math.abs

@Composable
fun SQSlider(
    modifier: Modifier = Modifier,
    availableOptions : List<Float> = listOf(5f, 10f, 15f, 20f),
    sliderInitialPosition : Float,
    title : String? = null,
    titleAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {

    val valueRange = 5f..20f
    val isEnabled = true
    var sliderPosition by remember { mutableStateOf(sliderInitialPosition) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title?.let {
            SQText(modifier = Modifier.align(titleAlignment), text = it)
        }

        if(isEnabled){
            Slider(
                value = sliderPosition,
                onValueChange = { value ->
                    val closestNumber = availableOptions.minByOrNull { abs(value - it ) }
                    sliderPosition = closestNumber?:value
                },
                valueRange = valueRange,
                steps = 2
            )
        }

        if(sliderPosition in availableOptions)
            SQText(text = sliderPosition.toInt().toString(), style = SQStyle.TextLatoBold)
    }
}

@Preview
@Composable
fun SQSliderPreview() {
    SQSlider(
        sliderInitialPosition = 5f,
        title = "Número de perguntas por categoria"
    )
}
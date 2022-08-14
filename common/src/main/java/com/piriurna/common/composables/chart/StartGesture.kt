package com.piriurna.common.composables.chart

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.models.PieChartSectionArea
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.atan2

@Composable
fun Modifier.startGesture(
    pieWidth: Float = 735f,
    onStart: (angleClicked : Double) -> Unit = { },
    onCancel: (angleClicked : Double) -> Unit = { },
    onCompleted: (angleClicked : Double) -> Unit = { }
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.pointerInput(interactionSource) {
        forEachGesture {
            coroutineScope {
                awaitPointerEventScope {

                    val tap = awaitFirstDown().also { it.consumeDownChange() }

                    val clickedAngle = convertTouchEventPointToAngle(
                        width = pieWidth,
                        xPos = tap.position.x,
                        yPos = tap.position.y
                    )

                    onStart(clickedAngle)

                    val up = waitForUpOrCancellation()
                    if (up == null) {
                        onCancel(clickedAngle)
                    } else {
                        up.consumeDownChange()
                        onCompleted(clickedAngle)
                    }
                }
            }
        }
    }
}

private fun convertTouchEventPointToAngle(
    width: Float,
    height: Float = width,
    xPos: Float,
    yPos: Float
): Double {

    val xCenter = width
    val yCenter = height

    val x = xPos - xCenter
    val y = yPos - yCenter

    var angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble()))
    angle = if (angle < 0) angle + 360 else angle
    return angle
}

@Preview(showBackground = true)
@Composable
fun StartGesturePreview() {
    Box(Modifier.fillMaxSize()) {
        SQPieChart(
            modifier = Modifier ,
            sections = PieChartSection.getMockPieChartSections(),

        )
    }
}
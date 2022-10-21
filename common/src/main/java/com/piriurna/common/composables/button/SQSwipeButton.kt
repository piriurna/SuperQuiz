package com.piriurna.common.composables.button

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.models.ConfirmationState
import com.piriurna.common.theme.SQStyle.TextLato12
import com.piriurna.common.theme.SQStyle.TextLato16
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.purple
import kotlin.math.roundToInt



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SQSwipeToConfirmButton(
    modifier: Modifier = Modifier,
    buttonText : String,
    confirmText : String = stringResource(R.string.swipe_to_confirm),
    backgroundDefaultColor : Color = purple,
    backgroundCompleteColor : Color = errorColor,
    onComplete : () -> Unit,
    sliderEnabled : Boolean = true,
    shouldSwipeBack : Boolean = false
) {

    var sliderWidth by remember {
      mutableStateOf(350)
    }

    val sliderWidthDp =
        with(LocalDensity.current) {
            sliderWidth.toDp()
        }

    val dragSize = 50.dp

    val swipeableState = rememberSwipeableState(ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) { (sliderWidthDp - dragSize).toPx() }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)
    val progress by derivedStateOf {
        if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
    }

    LaunchedEffect(progress) {
        if(progress == 1f) {
            onComplete()
        }
    }

    LaunchedEffect(key1 = shouldSwipeBack) {
        if(shouldSwipeBack) {
            swipeableState.animateTo(ConfirmationState.DEFAULT)
        }
    }

    val inverseRation: Float = 1f - progress
    val r = backgroundCompleteColor.red * progress + backgroundDefaultColor.red * inverseRation
    val g = backgroundCompleteColor.green * progress + backgroundDefaultColor.green * inverseRation
    val b = backgroundCompleteColor.blue * progress + backgroundDefaultColor.blue * inverseRation
    val backgroundColor = Color(r, g, b)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.9f) },
                orientation = Orientation.Horizontal,
                enabled = sliderEnabled
            )
            .background(backgroundColor, RoundedCornerShape(dragSize))
            .onSizeChanged {
                sliderWidth = it.width
            }
    ) {
        Column(
            Modifier
                .align(Alignment.Center)
                .alpha(1f - progress),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SQText(buttonText, color = Color.White, style = TextLato16)
            SQText(confirmText, color = Color.White, style = TextLato12)
        }

        DraggableControl(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(dragSize),
            progress = progress,
            backgroundColor = backgroundColor
        )
    }

}

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float,
    backgroundColor : Color
) {
    Box(
        modifier
            .padding(4.dp)
            .shadow(elevation = 2.dp, CircleShape, clip = false)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val isConfirmed = derivedStateOf { progress >= 0.8f }
        Crossfade(targetState = isConfirmed.value) {
            if (it) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = backgroundColor
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = backgroundColor
                )
            }

        }
    }
}

@Preview
@Composable
fun SQSwipeButtonPreview() {

    var enabled by remember {
       mutableStateOf(true)
    }
    SQSwipeToConfirmButton(
        buttonText = "Delete all Information",
        onComplete = {
            enabled = false
        },
        sliderEnabled = enabled
    )
}
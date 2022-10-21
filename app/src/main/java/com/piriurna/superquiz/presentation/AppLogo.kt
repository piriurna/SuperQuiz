package com.piriurna.superquiz.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    shouldAnimate : Boolean = true
) {
    var showingHorse by remember { mutableStateOf(true) }
    var showingRook by remember { mutableStateOf(false) }
    var showingQueen by remember { mutableStateOf(false) }


    var isFirstTime by remember { mutableStateOf(true) }
    var animate by remember { mutableStateOf(false) }

    var currentAngle by remember { mutableStateOf(0f) }

    var rotationDirection by remember { mutableStateOf(360f) }

    val angle by animateFloatAsState(
        targetValue = if(animate && shouldAnimate) currentAngle + rotationDirection else 0f,
        animationSpec = tween(1500, easing = LinearOutSlowInEasing),
        finishedListener = {
            when {
                showingHorse -> {
                    showingHorse = false
                    showingRook = true
                    showingQueen = false
                }

                showingRook -> {
                    showingHorse = false
                    showingRook = false
                    showingQueen = true
                }

                showingQueen -> {
                    showingHorse = true
                    showingRook = false
                    showingQueen = false
                }
            }
            if(currentAngle >= Float.MAX_VALUE - 720F){
                rotationDirection = -360f
            } else if(currentAngle <= 720f) {
                rotationDirection = 360f
            }

            currentAngle = it
        }
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(
            visible = showingHorse,
            enter = scaleIn(animationSpec = tween(200, easing = LinearEasing)),
            exit = scaleOut(animationSpec = tween(200, easing = LinearEasing)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chess_knight),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier
                    .size(156.dp)
                    .graphicsLayer {
                        rotationZ = angle

                    },
            )
        }

        AnimatedVisibility(
            visible = showingRook,
            enter = scaleIn(animationSpec = tween(200, easing = LinearEasing)),
            exit = scaleOut(animationSpec = tween(200, easing = LinearEasing)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chess_rook),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier
                    .size(156.dp)
                    .graphicsLayer {
                        rotationZ = angle

                    },
            )
        }

        AnimatedVisibility(
            visible = showingQueen,
            enter = scaleIn(animationSpec = tween(200, easing = LinearEasing)),
            exit = scaleOut(animationSpec = tween(200, easing = LinearEasing)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chess_queen),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier
                    .size(156.dp)
                    .graphicsLayer {
                        rotationZ = angle

                    },
            )
        }
    }

    if(isFirstTime) {
        isFirstTime = false
        animate = shouldAnimate
    }

}

@Preview(showBackground = true)
@Composable
fun AppLogoPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppLogo(shouldAnimate = false)
    }
}
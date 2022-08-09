package com.piriurna.superquiz.presentation.splash

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.R
import com.piriurna.superquiz.ui.theme.*

@Composable
fun SplashScreen() {
//    val splashViewModel : SplashViewModel = hiltViewModel()
//
//    val state = splashViewModel.state.value

    val infiniteTransition = rememberInfiniteTransition()

    val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 2000
                    0F at 0
                    -50F at 500
                    0F at 1000
                    130F at 1500
                    360F at 2000
                }
            )
        )

    val topStartAngle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 2000
                0F at 0
                -100F at 500
                -200F at 1000
                -300F at 1500
                360F at 2000
            }
        )
    )

    val bottomEndAngle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0F at 0
                -100F at 250
                -200F at 500
                -100F at 750
                0F at 1000
            }
        )
    )

    val color by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = Color.Yellow,
        animationSpec = InfiniteRepeatableSpec(
            animation = keyframes {
                durationMillis = 2000

                Color.Black at 0

                purple at 500

                primaryGreen at 1000

                primaryBlue at 1500

                Color.Yellow at 2000
            }
        )
    )

    val rapidColorAnimated by infiniteTransition.animateColor(
        initialValue = lightPurple,
        targetValue = orange,
        animationSpec = InfiniteRepeatableSpec(
            animation = keyframes {
                durationMillis = 1000

                lightPurple at 0

                purple at 250

                primaryBlue at 750

                orange at 1000
            }
        )
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Icon",
            modifier = Modifier
                .size(156.dp)
                .align(Alignment.TopStart)
                .graphicsLayer {
                    rotationZ = topStartAngle

                },
            colorFilter = ColorFilter.tint(color)


        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Icon",
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .size(156.dp)
                .graphicsLayer {
                    rotationZ = angle
                },


        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Icon",
            modifier = Modifier
                .size(156.dp)
                .align(Alignment.BottomEnd)
                .graphicsLayer {
                    rotationZ = bottomEndAngle
                },
            colorFilter = ColorFilter.tint(rapidColorAnimated)


        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}
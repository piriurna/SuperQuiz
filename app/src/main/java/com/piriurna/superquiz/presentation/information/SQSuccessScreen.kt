package com.piriurna.superquiz.presentation.information

import android.os.Handler
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.SQStyle.TextLatoThin18
import com.piriurna.superquiz.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SQSuccessScreen() {
    
    var imageVisible by remember {
        mutableStateOf(false)
    }

    var titleVisible by remember {
        mutableStateOf(false)
    }

    var subtitleVisible by remember {
        mutableStateOf(false)
    }

    var buttonVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        titleVisible = true

        delay(200)
        subtitleVisible = true

        delay(200)
        buttonVisible = true

        delay(400)
        imageVisible = true
    }


    Box(Modifier.fillMaxSize().padding(32.dp)) {
        AnimatedVisibility(
            modifier = Modifier
                .align(TopCenter)
                .padding(top = 90.dp),
            visible = imageVisible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Image(
                modifier = Modifier
                    .align(Center)
                    .size(250.dp),
                painter = painterResource(id = R.drawable.ic_checked_correct),
                contentDescription = "Congratulations image"
            )
        }



        Column(
            modifier = Modifier
                .align(Center)
                .size(350.dp)
                .padding(top = 240.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = titleVisible,
                enter = slideInVertically(initialOffsetY = { 100 }),
                exit = slideOutVertically()
            ) {
                SQText(text = "Congratulations!", style = TextLato27Bold)
            }

            AnimatedVisibility(
                visible = subtitleVisible,
                enter = slideInVertically(initialOffsetY = { 100 }),
                exit = slideOutVertically()

            ) {
                SQText(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "Now you have a strong profile which will make it possible for us to match you with good assignments.",
                    style = TextLatoThin18,
                    textAlign = TextAlign.Center
                )
            }

        }

        AnimatedVisibility(
            modifier = Modifier.align(BottomCenter).padding(bottom = 32.dp),
            visible = buttonVisible,
            enter = slideInVertically(initialOffsetY = { 100 }),
            exit = slideOutVertically()

        ) {
            SQButton(onClick = { /*TODO*/ }, buttonText = "Get more quizes")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun SQSuccessScreenPreview() {
    SQSuccessScreen()
}
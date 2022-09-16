package com.piriurna.superquiz.presentation.information

import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.SQStyle.TextLatoThin18
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.information.models.SuccessEvents
import com.piriurna.superquiz.presentation.information.models.SuccessState
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    categoryId: Int
) {
    val viewModel : SuccessViewModel = hiltViewModel()

    BuildSuccessScreen(categoryId,viewModel.state.value, viewModel::onTriggerEvent)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BuildSuccessScreen(
    categoryId : Int,
    state : SuccessState,
    events: ((SuccessEvents) -> Unit)? = null
) {
    
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

    val correctAnswers = state.categoryStatistics.correctAnswers.toFloat()
    val totalNumberOfQuestions = state.categoryStatistics.totalNumberOfQuestions.toFloat()
    val percentage by animateIntAsState(
        animationSpec = tween(1000),
        targetValue = ((correctAnswers/totalNumberOfQuestions) * 100).toInt()
    )

    LaunchedEffect(Unit) {
        events?.invoke(SuccessEvents.GetCategoryStatistics(categoryId))
        titleVisible = true

        delay(200)
        subtitleVisible = true

        delay(200)
        buttonVisible = true

        delay(400)
        imageVisible = true
    }


    Box(
        Modifier
            .fillMaxSize()
            .padding(32.dp)) {
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
                SQText(text = "You got ${percentage}% Correct!", style = TextLato27Bold)
            }

            AnimatedVisibility(
                visible = subtitleVisible,
                enter = slideInVertically(initialOffsetY = { 100 }),
                exit = slideOutVertically()

            ) {
                SQText(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "Now you can load new questions for the category or go back to the main screen.",
                    style = TextLatoThin18,
                    textAlign = TextAlign.Center
                )
            }

        }

        AnimatedVisibility(
            modifier = Modifier
                .align(BottomCenter)
                .padding(bottom = 32.dp),
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
fun SuccessScreenPreview() {
    var state by remember {
        mutableStateOf(SuccessState(isLoading = true))
    }

    LaunchedEffect(Unit) {
        state = state.copy(
            isLoading = false,
            categoryStatistics = CategoryStatistics(
                categoryId = Category.mockCategoryList[0].id,
                totalNumberOfQuestions = 100,
                correctAnswers = 80,
                incorrectAnswers = Question.mockQuestions.count { !it.isQuestionAnsweredCorrectly() }
            )
        )
    }
    BuildSuccessScreen(Category.mockCategoryList[0].id, state)
}
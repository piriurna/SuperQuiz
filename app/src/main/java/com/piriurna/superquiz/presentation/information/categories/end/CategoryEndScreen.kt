package com.piriurna.superquiz.presentation.information.categories.end

import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.SQStyle.TextLatoThin18
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.primaryGreen
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndDestination
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndEvents
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndState
import com.piriurna.superquiz.presentation.navigation.NavigationArguments
import com.piriurna.superquiz.presentation.navigation.PlayGamesDestinations
import com.piriurna.superquiz.presentation.navigation.utils.getArgument
import kotlinx.coroutines.delay

@Composable
fun CategoryEndScreen(
    navController: NavHostController
) {
    val viewModel : CategoryEndViewModel = hiltViewModel()

    val state = viewModel.state.value

    LaunchedEffect(key1 = viewModel.state.value) {
        when(viewModel.state.value.destination) {
            CategoryEndDestination.GO_TO_QUESTIONS -> {
                navController.popBackStack()
                navController.navigate(PlayGamesDestinations.Questions.withArgs(
                    state.category?.id
                ))
            }
            else -> {}
        }
    }

    BuildCategoryEndScreen(state, viewModel::onTriggerEvent)

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BuildCategoryEndScreen(
    state : CategoryEndState,
    events: ((CategoryEndEvents) -> Unit)? = null,
    initialAnimationState : Boolean = false
) {
    
    var imageVisible by remember {
        mutableStateOf(initialAnimationState)
    }

    var titleVisible by remember {
        mutableStateOf(initialAnimationState)
    }

    val percentage by animateIntAsState(
        animationSpec = tween(1000),
        targetValue = state.category?.getPercentageOfCorrectAnswers()?:0
    )


    val statusTitle = stringResource(id = state.getTitleResource(), percentage)

    val statusSubTitle = stringResource(state.getSubtitleResource())

    LaunchedEffect(Unit) {
        imageVisible = true

        delay(200)
        titleVisible = true
    }


    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp)
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = imageVisible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp),
                painter = painterResource(id = state.getImageResource()),
                contentDescription = "Congratulations image"
            )
        }




        AnimatedVisibility(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            visible = titleVisible,
            enter = slideInVertically(initialOffsetY = { 100 }),
            exit = slideOutVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SQText(text = statusTitle, style = TextLato27Bold, textAlign = TextAlign.Center)

                SQText(
                    modifier = Modifier.padding(top = 12.dp),
                    text = statusSubTitle,
                    style = TextLatoThin18,
                    textAlign = TextAlign.Center
                )
            }
        }


        SQButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            onClick = { events?.invoke(CategoryEndEvents.FetchMoreQuestions) },
            buttonText = "Get more quizes",
            backgroundColor = state.getButtonColor()
        )

    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun CategoryEndScreenPreview() {
    val state = CategoryEndState(
        isLoading = false,
        category = Category(
            id = Category.mockCategoryList[0].id,
            completionRate = 80,
            name = Category.mockCategoryList[0].name,
            totalNumberOfQuestions = 100,
            title =Category.mockCategoryList[0].title,
            correctAnswers = 80,
            incorrectAnswers = Question.mockQuestions.count { !it.isQuestionAnsweredCorrectly() }
        )
    )

    BuildCategoryEndScreen(state, initialAnimationState = true)
}
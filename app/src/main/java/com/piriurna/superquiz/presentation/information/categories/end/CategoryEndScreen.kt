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
    navBackStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    val viewModel : CategoryEndViewModel = hiltViewModel()

    val categoryId = navBackStackEntry.getArgument(NavigationArguments.CATEGORY_ID)?.toInt()


    LaunchedEffect(key1 = viewModel.state.value) {
        when(viewModel.state.value.destination) {
            CategoryEndDestination.GO_TO_QUESTIONS -> {
                navController.popBackStack()
                navController.navigate(PlayGamesDestinations.Questions.withArgs(
                    categoryId
                ))
            }
            else -> {}
        }
    }

    if(categoryId != null) {
        BuildCategoryEndScreen(categoryId,viewModel.state.value, viewModel::onTriggerEvent)
    } else {
        //Todo: 404 screen or similar
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BuildCategoryEndScreen(
    categoryId : Int,
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

    val correctAnswers = state.category?.correctAnswers?.toFloat() ?: 0F
    val totalNumberOfQuestions = state.category?.totalNumberOfQuestions?.toFloat() ?: 0F
    val percentage by animateIntAsState(
        animationSpec = tween(1000),
        targetValue = ((correctAnswers/totalNumberOfQuestions) * 100).toInt()
    )

    val statusImage = if(state.category?.isSuccess() == true) R.drawable.ic_checked_correct else R.drawable.ic_unchecked_incorrect

    val statusTitle = if(state.category?.isSuccess() == true) "You got $percentage% Correct!" else "You only got ${percentage}% Correct..."

    val statusSubTitle = if(state.category?.isSuccess() == true) "Now you can load new questions for the category or go back to the main screen." else "You can get more questions to try again or go back to the main screen."

    val buttonColor = if(state.category?.isSuccess() == true) primaryGreen else errorColor

    LaunchedEffect(Unit) {
        events?.invoke(CategoryEndEvents.GetCategoryStatistics(categoryId))

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
                painter = painterResource(id = statusImage),
                contentDescription = "Congratulations image"
            )
        }



        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                visible = titleVisible,
                enter = slideInVertically(initialOffsetY = { 100 }),
                exit = slideOutVertically()
            ) {
                Column {
                    SQText(text = statusTitle, style = TextLato27Bold, textAlign = TextAlign.Center)

                    SQText(
                        modifier = Modifier.padding(top = 12.dp),
                        text = statusSubTitle,
                        style = TextLatoThin18,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }


        SQButton(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            onClick = { events?.invoke(CategoryEndEvents.FetchMoreQuestions(categoryId)) },
            buttonText = "Get more quizes",
            backgroundColor = buttonColor
        )

    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
fun CategoryEndScreenPreview() {
    var state by remember {
        mutableStateOf(CategoryEndState(isLoading = true))
    }

    LaunchedEffect(Unit) {
        state = state.copy(
            isLoading = false,
            category = Category(
                id = Category.mockCategoryList[0].id,
                name = Category.mockCategoryList[0].name,
                totalNumberOfQuestions = 100,
                title =Category.mockCategoryList[0].title,
                correctAnswers = 80,
                incorrectAnswers = Question.mockQuestions.count { !it.isQuestionAnsweredCorrectly() }
            )
        )
    }
    BuildCategoryEndScreen(Category.mockCategoryList[0].id, state, initialAnimationState = true)
}
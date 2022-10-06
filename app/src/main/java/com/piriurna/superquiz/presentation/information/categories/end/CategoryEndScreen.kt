package com.piriurna.superquiz.presentation.information.categories.end

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
import androidx.navigation.NavBackStackEntry
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.SQStyle.TextLatoThin18
import com.piriurna.common.theme.errorColor
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndEvents
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndState
import com.piriurna.superquiz.presentation.navigation.NavigationArguments
import com.piriurna.superquiz.presentation.navigation.utils.getArgument
import com.piriurna.superquiz.ui.theme.primaryGreen
import kotlinx.coroutines.delay

@Composable
fun CategoryEndScreen(
    navBackStackEntry: NavBackStackEntry
) {
    val viewModel : CategoryEndViewModel = hiltViewModel()

    val categoryId = navBackStackEntry.getArgument(NavigationArguments.CATEGORY_ID)?.toInt()

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
    events: ((CategoryEndEvents) -> Unit)? = null
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

    val correctAnswers = state.category?.correctAnswers?.toFloat() ?: 0F
    val totalNumberOfQuestions = state.category?.totalNumberOfQuestions?.toFloat() ?: 0F
    val percentage by animateIntAsState(
        animationSpec = tween(1000),
        targetValue = ((correctAnswers/totalNumberOfQuestions) * 100).toInt()
    )

    //TODO REFACTOR, PUT IT ALL IN THE STATISTICS MODEL OR THE NEW CATEGORY MODEL
    val statusImage = if(state.category?.isSuccess() == true) R.drawable.ic_checked_correct else R.drawable.ic_unchecked_incorrect

    val statusTitle = if(state.category?.isSuccess() == true) "You got $percentage% Correct!" else "You only got ${percentage}% Correct..."

    val statusSubTitle = if(state.category?.isSuccess() == true) "Now you can load new questions for the category or go back to the main screen." else "You can get more questions to try again or go back to the main screen."

    val buttonColor = if(state.category?.isSuccess() == true) primaryGreen else errorColor

    LaunchedEffect(Unit) {
        events?.invoke(CategoryEndEvents.GetCategoryStatistics(categoryId))
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
                painter = painterResource(id = statusImage),
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
                SQText(text = statusTitle, style = TextLato27Bold)
            }

            AnimatedVisibility(
                visible = subtitleVisible,
                enter = slideInVertically(initialOffsetY = { 100 }),
                exit = slideOutVertically()

            ) {
                SQText(
                    modifier = Modifier.padding(top = 12.dp),
                    text = statusSubTitle,
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
            SQButton(onClick = { /*TODO*/ }, buttonText = "Get more quizes", backgroundColor = buttonColor)
        }


    }
}

@Preview(showBackground = true)
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
                correctAnswers = 30,
                incorrectAnswers = Question.mockQuestions.count { !it.isQuestionAnsweredCorrectly() }
            )
        )
    }
    BuildCategoryEndScreen(Category.mockCategoryList[0].id, state)
}
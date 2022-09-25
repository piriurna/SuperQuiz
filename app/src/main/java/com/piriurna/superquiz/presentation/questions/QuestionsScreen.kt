package com.piriurna.superquiz.presentation.questions

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.alert.SQAlertDialog
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.chip.SQChip
import com.piriurna.common.composables.progress.SQProgressBar
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.theme.lightOrange
import com.piriurna.common.theme.orange
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.composables.AnswerAlertPanel
import com.piriurna.superquiz.presentation.composables.models.disabledHorizontalPointerInputScroll
import com.piriurna.superquiz.presentation.navigation.HomeDestinationScreen
import com.piriurna.superquiz.presentation.navigation.NavigationArguments
import com.piriurna.superquiz.presentation.navigation.PlayGamesDestinations
import com.piriurna.superquiz.presentation.navigation.utils.getArgument
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.min

const val NUMBER_OF_QUESTIONS_DISABLED_ON_HINT = 2

@Composable
fun QuestionsScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController
) {

    val viewModel : QuestionsViewModel = hiltViewModel()


    val categoryId = navBackStackEntry.getArgument(NavigationArguments.CATEGORY_ID)?.toInt()

    if(categoryId != null) {
        BuildQuestionsScreen(
            categoryId = categoryId,
            state = viewModel.state.value,
            events = viewModel::onTriggerEvent,
            navController
        )
    } else {
        //todo: show a 404 screen or similar
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun BuildQuestionsScreen(
    categoryId : Int,
    state: QuestionsState,
    events : ((QuestionsEvents) -> Unit)? = null,
    navController: NavController = rememberNavController()
) {

    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        events?.invoke(QuestionsEvents.GetQuestions(categoryId))
    }

    val questions = state.categoryQuestions.sortedBy { it.index }

    val numOfQuestions = state.categoryQuestions.size

    val questionIndex = questions.getOrNull(pagerState.currentPage)?.index?:0
    val percentage = ((questionIndex + 1).toFloat() / numOfQuestions.toFloat()) * 100

    var selectedAnswer by remember {
        mutableStateOf<Answer?>(null)
    }

    var shouldShowAlert by remember {
        mutableStateOf(false)
    }

    var isAnswered by remember {
        mutableStateOf(false)
    }

    // create variable for current time
    var currentMinute by remember {
        mutableStateOf(0)
    }

    // create variable for current time
    var currentSec by remember {
        mutableStateOf(0L)
    }

    var isCategoryEmpty by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = state.categoryQuestions, key2 = state.isLoading) {
        isCategoryEmpty = !state.isLoading && state.categoryQuestions.isEmpty()
    }

    LaunchedEffect(key1 = currentSec) {
        if(currentSec < 60000) {
            delay(100L)
            currentSec += 100L
        } else {
            currentMinute++
            currentSec = 0L
        }
    }

    SQScaffold(isLoading = state.isLoading) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
                if(questions.isNotEmpty()) {
                    val currentQuestion = questions[pagerState.currentPage]

                    val isHintVisible by derivedStateOf {
                        currentQuestion.isHintAvailable()
                    }
                    SQProgressBar(
                        progress = percentage.toInt(),
                        percentageText = "${currentQuestion.index + 1}/${numOfQuestions}",
                        textIncompleteColor = Color.Black,
                        chipIcon = ImageVector.vectorResource(R.drawable.ic_timer),
                        chipForegroundColor = orange,
                        chipBackgroundColor = lightOrange,
                        chipText = "${currentMinute}min ${currentSec/1000L}s",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        HorizontalPager(
                            count = questions.size,
                            state = pagerState,
                            modifier = Modifier.disabledHorizontalPointerInputScroll()
                        ) { index ->
                            SQQuestionCard(
                                question = questions[index],
                                questionIndex = currentQuestion.index + 1,
                                onAnswerSelected = { answer ->
                                    selectedAnswer = answer
                                },
                                contentEnabled = !isAnswered,
                                enabled = false
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            AnimatedVisibility(
                                visible = isHintVisible,
                                enter = scaleIn(animationSpec = spring(Spring.DampingRatioHighBouncy)),
                                exit = scaleOut()
                            ) {
                                SQChip(
                                    text = "Hints",
                                    icon = ImageVector.vectorResource(id = R.drawable.ic_light_bulb_hint),
                                    onClick = {
                                        events?.invoke(QuestionsEvents.PerformHintAction(currentQuestion))
                                    },
                                    foregroundColor = purple,
                                    backgroundColor = lightPurple
                                )
                            }
                        }
                    }
                }


            }
            AnimatedVisibility(
                visible = shouldShowAlert,
                enter = scaleIn(animationSpec = spring(Spring.DampingRatioLowBouncy)),
                exit = scaleOut()
            ) {
                AnswerAlertPanel(
                    topText = "Correct Answer",
                    topBadge = Icons.Default.Done,
                    middleText = "\"All good things come to those who wait.\"",
                    bottomText = "- Paulina Simons"
                )
            }


            SQButton(
                onClick = {
                    scope.launch {
                        if(isAnswered) {
                            isAnswered = false

                            if(pagerState.currentPage == pagerState.pageCount - 1) {
                                navController.navigate(PlayGamesDestinations.CategoryCompleted.withArgs(categoryId))
                            } else {
                                val nextPage = min(pagerState.pageCount - 1, pagerState.currentPage + 1)
                                pagerState.animateScrollToPage(nextPage)
                            }
                        } else {
                            selectedAnswer?.let { answer ->
                                val question = questions[pagerState.currentPage]
                                events?.invoke(QuestionsEvents.SaveAnswer(question.id, answer))
                                isAnswered = true
                            }
                        }
                        shouldShowAlert = questions[pagerState.currentPage].getCorrectAnswer() == selectedAnswer
                    }
                },
                buttonText = if(isAnswered) "NEXT" else "SEND",
                enabled = selectedAnswer != null,
                modifier= Modifier
                    .fillMaxWidth(),
            )
            AnimatedVisibility(
                visible = isCategoryEmpty,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SQAlertDialog(
                    title = "You ran out of questions",
                    description = "Would you like to get more questions for this category?",
                    okLabel = "Get Questions",
                    okClick = {
                        events?.invoke(QuestionsEvents.FetchQuestionsForCategory(categoryId))
                    },
                    themeColor = Color.Green.copy(alpha = 0.5f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionScreenPreview() {
    Column(Modifier.fillMaxSize()) {
        BuildQuestionsScreen(categoryId = 9, state = QuestionsState(
            categoryQuestions = Question.mockQuestions
        ))

    }
}
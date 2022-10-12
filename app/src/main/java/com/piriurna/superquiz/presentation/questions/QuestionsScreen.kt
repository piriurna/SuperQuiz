package com.piriurna.superquiz.presentation.questions

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
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

    val state = viewModel.state.collectAsState()

    val categoryId = navBackStackEntry.getArgument(NavigationArguments.CATEGORY_ID)?.toInt()

    if(categoryId != null) {
        BuildQuestionsScreen(
            state = state.value,
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
    state: QuestionsState,
    events : ((QuestionsEvents) -> Unit)? = null,
    navController: NavController = rememberNavController()
) {

    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()

    val questions = state.categoryQuestions

    val numOfQuestions = state.categoryQuestions.size


    val currentQuestion by derivedStateOf {
        questions.find { it.id == state.lastAnsweredQuestionId }
    }

    val questionIndex = questions.indexOf(currentQuestion)

    val percentage by animateFloatAsState(((questionIndex + 1).toFloat() / numOfQuestions.toFloat()) * 100)

    var selectedAnswer by remember {
        mutableStateOf<Answer?>(null)
    }

    var answerAlreadySent by remember {
        mutableStateOf(false)
    }

    val isCategoryEmpty by derivedStateOf {
        !state.isLoading && questionIndex == -1
    }

    var currentMinute by remember {
        mutableStateOf(0)
    }

    var currentSec by remember {
        mutableStateOf(0L)
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

    LaunchedEffect(key1 = questionIndex, key2 = pagerState.pageCount) {
        scope.launch {
            if(pagerState.pageCount != 0 && questionIndex != -1 && pagerState.pageCount > questionIndex){
                pagerState.scrollToPage(questionIndex)
                selectedAnswer = null
            }
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
                currentQuestion?.let { question ->

                    val isHintVisible by derivedStateOf {
                        question.isHintAvailable() && !answerAlreadySent
                    }
                    SQProgressBar(
                        progress = percentage.toInt(),
                        percentageText = "${questionIndex + 1}/${numOfQuestions}",
                        textIncompleteColor = Color.Black,
                        chipIcon = ImageVector.vectorResource(R.drawable.ic_timer),
                        chipForegroundColor = orange,
                        chipBackgroundColor = lightOrange,
                        chipText = "${currentMinute}min ${currentSec / 1000L}s",
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
                                questionIndex = index + 1,
                                onAnswerSelected = { answer ->
                                    selectedAnswer = answer
                                },
                                contentEnabled = !answerAlreadySent,
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
                                        events?.invoke(QuestionsEvents.PerformHintAction(question))
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
                visible = answerAlreadySent,
                enter = scaleIn(animationSpec = spring(Spring.DampingRatioLowBouncy)),
                exit = scaleOut()
            ) {
                AnswerAlertPanel(
                    isCorrect = selectedAnswer == currentQuestion?.getCorrectAnswer(),
                    quote = state.quotes[pagerState.currentPage]
                )
            }


            SQButton(
                onClick = {
                    scope.launch {
                        if(answerAlreadySent) {
                            answerAlreadySent = false
                            if(state.isLastQuestion(pagerState.currentPage)) {
                                currentQuestion?.let {
                                    navController.navigate(PlayGamesDestinations.CategoryCompleted.withArgs(it.categoryId))
                                }
                            }
                        } else {
                            selectedAnswer?.let { answer ->

                                currentQuestion?.let { question ->

                                    events?.invoke(QuestionsEvents.SaveAnswer(question, answer))
                                    answerAlreadySent = true
                                }

                            }
                        }
                    }
                },
                buttonText = if(answerAlreadySent) "NEXT" else "SEND",
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
                        currentQuestion?.let {
                            events?.invoke(QuestionsEvents.FetchQuestionsForCategory(it.categoryId))
                        }
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
        BuildQuestionsScreen(
            state = QuestionsState(
                categoryQuestions = Question.mockQuestions
            )
        )

    }
}
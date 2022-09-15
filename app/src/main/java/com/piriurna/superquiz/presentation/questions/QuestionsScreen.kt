package com.piriurna.superquiz.presentation.questions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.domain.models.Answer
import com.piriurna.superquiz.presentation.composables.AnswerAlertPanel
import com.piriurna.common.composables.chip.SQChip
import com.piriurna.common.composables.progress.SQProgressBar
import com.piriurna.common.theme.lightOrange
import com.piriurna.common.theme.orange
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.questions.CategoryInformation
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.composables.models.disabledHorizontalPointerInputScroll
import com.piriurna.superquiz.presentation.playgames.PlayGamesEvents
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple
import kotlinx.coroutines.launch
import kotlin.math.min

const val NUMBER_OF_QUESTIONS_DISABLED_ON_HINT = 2

@Composable
fun QuestionsScreen(
    categoryId : Int
) {

    val viewModel : QuestionsViewModel = hiltViewModel()

    BuildQuestionsScreen(
        categoryId = categoryId,
        state = viewModel.state.value,
        events = viewModel::onTriggerEvent
    )
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun BuildQuestionsScreen(
    categoryId : Int,
    state: QuestionsState,
    events : ((QuestionsEvents) -> Unit)? = null,
) {

    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        events?.invoke(QuestionsEvents.GetQuestions(categoryId))
    }

    val questions = state.categoryInformation.questions

    val numOfQuestions = state.categoryInformation.numberOfQuestions

    SQScaffold(isLoading = state.isLoading) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val questionIndex = questions.getOrNull(pagerState.currentPage)?.index?:0
            val percentage = (questionIndex.toFloat() / numOfQuestions.toFloat()) * 100

            var selectedAnswer by remember {
                mutableStateOf<Answer?>(null)
            }

            var disabledAnswers by remember {
                mutableStateOf<List<Answer>>(emptyList())
            }

            var shouldShowAlert by remember {
                mutableStateOf(false)
            }

            var isAnswered by remember {
                mutableStateOf(false)
            }



            Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
                if(questions.isNotEmpty()) {
                    val currentQuestion = questions[pagerState.currentPage]

                    val isHintVisible by derivedStateOf {
                        disabledAnswers.isEmpty() && currentQuestion.isMultipleChoice()
                    }
                    SQProgressBar(
                        progress = percentage.toInt(),
                        percentageText = "${currentQuestion.index + 1}/${numOfQuestions}",
                        textIncompleteColor = Color.Black,
                        chipIcon = ImageVector.vectorResource(R.drawable.ic_timer),
                        chipForegroundColor = orange,
                        chipBackgroundColor = lightOrange,
                        chipText = "5min 55s",
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
                                disabledAnswers = disabledAnswers,
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
                                        if(disabledAnswers.isEmpty()) disabledAnswers = performHint(currentQuestion)
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
                            val nextPage = min(pagerState.pageCount - 1, pagerState.currentPage + 1)
                            pagerState.animateScrollToPage(nextPage)
                            disabledAnswers = emptyList()
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
        }
    }
}

private fun performHint(currentQuestion : Question) : List<Answer> {
    val mutableList = mutableListOf<Answer>()
    if(currentQuestion.isMultipleChoice()){
        repeat(NUMBER_OF_QUESTIONS_DISABLED_ON_HINT) {
            val enabledAnswers = currentQuestion.getIncorrectAnswers().filterNot { mutableList.contains(it) }
            mutableList.add(enabledAnswers.random())
        }
    }

    return mutableList
}

@Preview(showBackground = true)
@Composable
private fun QuestionScreenPreview() {
    Column(Modifier.fillMaxSize()) {
        BuildQuestionsScreen(categoryId = 9, state = QuestionsState(
            categoryInformation = CategoryInformation(questions = Question.mockQuestions, numberOfQuestions = Question.mockQuestions.size)
        ))

    }
}
package com.piriurna.superquiz.presentation.questions

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.domain.models.Answer
import com.piriurna.superquiz.presentation.composables.AnswerAlertPanel
import com.piriurna.common.composables.chip.SQChip
import com.piriurna.common.composables.progress.SQProgressBar
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.models.disabledHorizontalPointerInputScroll
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple
import kotlinx.coroutines.launch
import kotlin.math.min

const val NUMBER_OF_QUESTIONS_DISABLED_ON_HINT = 2

@OptIn(ExperimentalPagerApi::class)
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    categoryId : Int
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val viewModel : QuestionsViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(QuestionsEvents.GetQuestions(categoryId))
    }

    val state = viewModel.state.value

    val questions = state.categoryInformation.questions

    val numOfQuestions = state.categoryInformation.numberOfQuestions

    SQScaffold(isLoading = state.isLoading) {
        Column(
            modifier = modifier
                .padding(16.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val percentage = ((pagerState.currentPage.toFloat()/(questions.size-1)) * 100).toInt()

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
                    var isHintVisible by remember {
                        mutableStateOf(true)
                    }
                    val currentQuestion = questions[pagerState.currentPage]
                    SQProgressBar(
                        progress = percentage,
                        percentageText = "${currentQuestion.index + 1}/${numOfQuestions}",
                        textIncompleteColor = Color.Black,
                        chipIcon = Icons.Default.Info,
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

                            AnimatedVisibility(visible = isHintVisible) {
                                SQChip(
                                    text = "Hints",
                                    icon = Icons.Default.Home,
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
            if(shouldShowAlert){
                //GET THESE QUOTES FROM DOMAIN
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
                                viewModel.onTriggerEvent(QuestionsEvents.SaveAnswer(question.id, answer))
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
    val isMultipleChoice = currentQuestion.allAnswers.size > 2
    if(isMultipleChoice){
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
        QuestionsScreen(categoryId = 9)

    }
}
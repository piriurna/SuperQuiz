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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.piriurna.common.theme.lightPurple
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.purple
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.composables.AnswerAlertPanel
import com.piriurna.superquiz.presentation.composables.models.disabledHorizontalPointerInputScroll
import com.piriurna.superquiz.presentation.navigation.PlayGamesDestinations
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import kotlinx.coroutines.launch

@Composable
fun QuestionsScreen(
    navController: NavController
) {

    val viewModel : QuestionsViewModel = hiltViewModel()

    val state = viewModel.state.collectAsState()


    BuildQuestionsScreen(
        state = state.value,
        events = viewModel::onTriggerEvent,
        navController
    )

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

    LaunchedEffect(key1 = state.destination, key2 = state.currentQuestion) {

        when(state.destination) {
            QuestionDestination.SHOW_QUESTION -> {
                if(pagerState.currentPage != state.getCurrentQuestionIndex() && pagerState.pageCount != 0){
                    pagerState.animateScrollToPage(state.getCurrentQuestionIndex())

                }
            }
            QuestionDestination.GO_TO_RESULTS -> {
                navController.popBackStack()
                navController.navigate(
                    PlayGamesDestinations.CategoryCompleted.withArgs(
                        state.category.id
                    )
                )
            }
            else -> {

            }
        }
    }

    SQScaffold(isLoading = state.isLoading, error = state.error) {

        AnimatedVisibility(
            visible = state.destination == QuestionDestination.NO_QUESTIONS_AVAILABLE && !state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            SQAlertDialog(
                title = stringResource(R.string.you_ran_out_of_questions),
                description = stringResource(R.string.would_you_like_to_get_more_questions_for_this_category),
                laterLabel = stringResource(R.string.later),
                laterClick = {
                    events?.invoke(QuestionsEvents.DismissNoQuestionsPopup)
                    navController.popBackStack()
                },
                okLabel = stringResource(R.string.get_questions),
                okClick = {
                    events?.invoke(QuestionsEvents.FetchQuestionsForCategory(state.category.id))
                },
                themeColor = purple.copy(alpha = 0.5f)
            )
        }

        state.currentQuestion?.let { question ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {

                    val isHintVisible = question.isHintAvailable()

                    val progress by animateFloatAsState(targetValue = state.category.completionRate.toFloat())

                    SQProgressBar(
                        progress = progress.toInt(),
                        percentageText = "${state.getCurrentQuestionIndex()}/${state.questionsList.size}",
                        textIncompleteColor = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )



                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        HorizontalPager(
                            count = state.questionsList.size,
                            state = pagerState,
                            modifier = Modifier.disabledHorizontalPointerInputScroll()
                        ) { index ->

                            SQQuestionCard(
                                question = question,
                                questionIndex = state.getCurrentQuestionIndex(),
                                onAnswerSelected = { answer ->
                                    events?.invoke(QuestionsEvents.SelectAnswer(answer))
                                },
                                contentEnabled = !state.isShowingAnswerResult(),
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
                                    text = stringResource(R.string.hints),
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
                AnimatedVisibility(
                    visible = state.isShowingAnswerResult() && !state.isLoading,
                    enter = scaleIn(animationSpec = spring(Spring.DampingRatioLowBouncy))
                ) {
                    if (state.isShowingAnswerResult())
                        AnswerAlertPanel(
                            isCorrect = question.isQuestionAnsweredCorrectly(),
                            quote = state.quotes[pagerState.currentPage]
                        )
                }


                SQButton(
                    buttonText = stringResource(state.getButtonStringResource()).toUpperCase(Locale.current),
                    enabled = question.chosenAnswer != null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            if (state.isShowingAnswerResult()) {
                                events?.invoke(QuestionsEvents.GoToNextPage)
                            } else {
                                events?.invoke(QuestionsEvents.ShowResult)
                            }
                        }
                    },
                )
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
                questionsList = Question.mockQuestions
            )
        )

    }
}
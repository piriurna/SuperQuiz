package com.piriurna.superquiz.presentation.questions

import androidx.compose.foundation.clickable
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.AnswerAlertPanel
import com.piriurna.superquiz.presentation.composables.SQChip
import com.piriurna.superquiz.presentation.composables.SQProgressBar
import com.piriurna.superquiz.presentation.composables.models.disabledHorizontalPointerInputScroll
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple
import kotlinx.coroutines.launch
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
) {
    val questions = Question.mockQuestions
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val percentage = ((pagerState.currentPage.toFloat()/(questions.size-1)) * 100).toInt()

        var selectedAnswer by remember {
            mutableStateOf<String?>(null)
        }

        var shouldShowAlert by remember {
            mutableStateOf(false)
        }

        var isAnswered by remember {
            mutableStateOf(false)
        }

        Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
            SQProgressBar(
                progress = percentage,
                percentageText = "${pagerState.currentPage + 1}/${questions.size}",
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
                        questionIndex = index,
                        onAnswerSelected = {text ->
                            selectedAnswer = text
                        },
                        isEnabled = !isAnswered
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SQChip(
                        text = "Hints",
                        icon = Icons.Default.Home,
                        foregroundColor = purple,
                        backgroundColor = lightPurple
                    )
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


        Button(
            onClick = { scope.launch {
                if(isAnswered) {
                    isAnswered = false
                    val nextPage = min(pagerState.pageCount - 1, pagerState.currentPage + 1)
                    pagerState.animateScrollToPage(nextPage)
                }

                isAnswered = true
                shouldShowAlert = questions[pagerState.currentPage].correctAnswer == selectedAnswer
            } },
            enabled = selectedAnswer != null,
            modifier= Modifier
                .fillMaxWidth(),
        ) {
            Text(text = if(isAnswered) "NEXT" else "SEND")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun QuestionScreenPreview() {
    Column(Modifier.fillMaxSize()) {
        QuestionsScreen()

    }
}
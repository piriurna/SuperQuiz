package com.piriurna.superquiz.presentation.questions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.SQChip
import com.piriurna.superquiz.presentation.composables.SQProgressBar
import com.piriurna.superquiz.presentation.composables.models.ChipModel
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorModel
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorText
import com.piriurna.superquiz.presentation.questions.composables.SQQuestionCard
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple
import kotlinx.coroutines.launch

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

        Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
            SQProgressBar(
                progressIndicatorModel = ProgressIndicatorModel(
                    progress = percentage,
                    progressIndicatorText = ProgressIndicatorText.FractionText(
                        current = pagerState.currentPage + 1,
                        count = questions.size
                    ),
                    chipModel = ChipModel(
                        icon = Icons.Default.Info,
                        text = "5min 55s",
                    )
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                HorizontalPager(
                    count = questions.size,
                    state = pagerState,
                ) { index ->
                    SQQuestionCard(question = questions[index], index = index)
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


        Button(
            onClick = { scope.launch {
                pagerState.scrollToPage(pagerState.currentPage + 1)
            } },
            modifier= Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "SEND")
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
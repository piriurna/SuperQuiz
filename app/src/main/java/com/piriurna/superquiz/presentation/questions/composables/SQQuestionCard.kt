package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.SQCard
import com.piriurna.superquiz.presentation.questions.models.AnswerSelectedListener

@Composable
fun SQQuestionCard(
    modifier: Modifier = Modifier,
    question: Question,
    questionIndex : Int,
    answerSelectedListener : AnswerSelectedListener? = null
) {
    val answers = question.allAnswers

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        SQQuestion(index = questionIndex, question = question)

        SQCard {
            Column(modifier = Modifier.padding(8.dp)) {
                answers.forEach {
                    SQAnswerRow(text = it, answerSelectedListener = answerSelectedListener)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SQQuestionCardPreview() {
    SQQuestionCard(question = Question.mockQuestions[0], questionIndex = 0)
}
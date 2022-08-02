package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.SQCard

@Composable
fun SQQuestionCard(
    modifier: Modifier = Modifier,
    question: Question,
    index : Int
) {

    val answers = question.incorrectAnswers.toMutableList()
    answers.add(question.correctAnswer)

    answers.shuffle()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        SQQuestion(index = index, question = question)

        SQCard {
            Column(modifier = Modifier.padding(8.dp)) {
                answers.forEach {
                    SQAnswerRow(text = it)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SQQuestionCardPreview() {
    SQQuestionCard(question = Question.mockQuestions[0], index = 0)
}
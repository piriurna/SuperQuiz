package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.domain.models.Question

@Composable
fun SQQuestion(
    index : Int,
    question: Question
) {

    Column() {
        Row() {
            Text(
                text = "Q${index}.",
                maxLines = 1
            )

            Text(
                text = question.description,
                softWrap = true
            )
        }


    }


}


@Preview(showBackground = true)
@Composable
private fun SQQuestionPreview() {
    SQQuestion(
        index = 1,
        question = Question.mockQuestions[0]
    )
}
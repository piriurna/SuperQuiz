package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.composables.SQToggleRadioGroup

@Composable
fun SQQuestionCard(
    modifier: Modifier = Modifier,
    question: Question,
    questionIndex : Int,
    onAnswerSelected : (Answer) -> Unit = {},
    enabled : Boolean = true,
    contentEnabled : Boolean,
    onClick : (() -> Unit)? = null
) {
    val answers = question.allAnswers

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        SQQuestion(index = questionIndex, question = question)

        SQCard(
            enabled = enabled,
            onClick = { onClick?.invoke() }
        ) {
            SQToggleRadioGroup(
                modifier = Modifier.padding(8.dp),
                options = answers,
                onAnswerSelected = onAnswerSelected,
                isEnabled = contentEnabled
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SQQuestionCardPreview() {
    SQQuestionCard(question = Question.mockQuestions[0], questionIndex = 0, contentEnabled = true)
}
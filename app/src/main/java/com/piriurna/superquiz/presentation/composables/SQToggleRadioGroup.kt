package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.questions.composables.SQAnswerRow

@Composable
fun SQToggleRadioGroup(
    modifier: Modifier = Modifier,
    options: List<Answer>,
    onAnswerSelected : (Answer) -> Unit = {},
    isEnabled: Boolean
) {

    var selected by remember { mutableStateOf<Answer?>(null) }


    val onSelectionChange = { answer: Answer ->
        if(isEnabled) {
            selected = answer
            onAnswerSelected.invoke(answer)
        }
    }

    Column(modifier = modifier) {
        options.forEach {
            SQAnswerRow(
                answer = it,
                selected = selected == it,
                onClick = onSelectionChange
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SQToggleRadioGroupPreview() {
    SQToggleRadioGroup(options = Question.mockQuestions[0].allAnswers, isEnabled = true)
}
package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.domain.models.Question
import com.piriurna.superquiz.presentation.questions.composables.SQAnswerRow

@Composable
fun SQToggleRadioGroup(
    modifier: Modifier = Modifier,
    options: List<String>,
    onAnswerSelected : (String) -> Unit = {}
) {

    var selected by remember { mutableStateOf<String?>(null) }

    val onSelectionChange = { text: String ->
        selected = text
        onAnswerSelected.invoke(text)
    }

    Column(modifier = modifier) {
        options.forEach {
            SQAnswerRow(text = it, selected = selected == it, onClick = onSelectionChange)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SQToggleRadioGroupPreview() {
    SQToggleRadioGroup(options = Question.mockQuestions[0].allAnswers)
}
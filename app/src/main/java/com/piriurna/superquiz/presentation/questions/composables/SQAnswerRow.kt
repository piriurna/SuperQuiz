package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.superquiz.presentation.questions.models.AnswerSelectedListener

@Composable
fun SQAnswerRow(
    modifier: Modifier = Modifier,
    text: String,
    answerSelectedListener: AnswerSelectedListener? = null
) {

    var selected by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = answerSelectedListener?.isAnswerSelected(text) == true,
            onClick = {
                selected = !selected
                answerSelectedListener?.onAnswerSelected(text)
            }
        )

        Text(
            text = text,
            fontWeight = FontWeight.W500
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SQAnswerRowPreview() {
    SQAnswerRow(text = "text")
}
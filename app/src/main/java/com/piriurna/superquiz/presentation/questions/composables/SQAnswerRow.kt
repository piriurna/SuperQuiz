package com.piriurna.superquiz.presentation.questions.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.composables.text.SQText
import com.piriurna.domain.models.Answer

@Composable
fun SQAnswerRow(
    modifier: Modifier = Modifier,
    answer: Answer,
    selected : Boolean,
    onClick : (Answer) -> Unit
) {
    val alpha by animateFloatAsState(targetValue = if(answer.isEnabled) 1f else 0.5f)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = selected,
            onClick = {
                onClick(answer)
            },
            enabled = answer.isEnabled,
        )

        SQText(
            text = answer.description,
            modifier = Modifier.alpha(alpha)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SQAnswerRowPreview() {
    SQAnswerRow(answer = Answer.getFirstQuestionMockAnswers()[0], selected = false, onClick = {})
}
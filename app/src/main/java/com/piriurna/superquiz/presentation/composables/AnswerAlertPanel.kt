package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnswerAlertPanel() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Correct answer"
        )

       Text(
           text = "All good things come to those who wait.",
           textAlign = TextAlign.Center,
           softWrap = true,
           modifier = Modifier.padding(horizontal = 16.dp)
       )

       Text(text = "- Paulina Simons")

    }
}

@Preview(showBackground = true)
@Composable
private fun AnswerAlertPanelPreview() {
    AnswerAlertPanel()
}
package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.text.SQBadgeText
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.domain.models.quotes.Quote
import com.piriurna.superquiz.ui.theme.primaryGreen

@Composable
fun AnswerAlertPanel(
    modifier: Modifier = Modifier,
    isCorrect: Boolean,
    quote: Quote
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {

        val topBadge = if(isCorrect) Icons.Default.Done else Icons.Default.Close
        val topText = if(isCorrect) "Correct Answer" else "Incorrect Answer"
        val color = if(isCorrect) primaryGreen else Color.Red
        SQBadgeText(
            icon = topBadge,
            text = topText,
            foregroundColor = color
        )

       SQText(
           text = quote.content,
           textAlign = TextAlign.Center,
           style = SQStyle.TextLatoBold,
           modifier = Modifier.padding(horizontal = 16.dp),
       )

       SQText(
           text = "-${quote.author}",
           style = SQStyle.TextLatoThin
       )

    }
}

@Preview(showBackground = true)
@Composable
private fun AnswerAlertPanelPreview() {
    AnswerAlertPanel(
        isCorrect = true,
        quote = Quote.mockQuote
    )
}
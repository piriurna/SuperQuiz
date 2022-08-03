package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.primaryGreen

@Composable
fun AnswerAlertPanel(
    modifier: Modifier = Modifier,
    topText : String,
    topBadge: ImageVector,
    middleText : String? = null,
    bottomText: String? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {

        SQBadgeText(
            icon = topBadge,
            text = topText,
            foregroundColor = primaryGreen
        )

        middleText?.let {
           Text(
               text = it,
               textAlign = TextAlign.Center,
               softWrap = true,
               modifier = Modifier.padding(horizontal = 16.dp),
               fontWeight = FontWeight.W400
           )

        }

        bottomText?.let {
           Text(
               text = it,
               fontWeight = FontWeight.W300
           )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AnswerAlertPanelPreview() {
    AnswerAlertPanel(
        topBadge = Icons.Default.Done,
        topText = "Correct Answer",
        middleText = "\"All good things come to those who wait.\"",
        bottomText = "- Paulina Simons"
    )
}
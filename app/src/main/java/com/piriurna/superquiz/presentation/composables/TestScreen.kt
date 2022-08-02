package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorModel
import com.piriurna.superquiz.ui.theme.lightOrange
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun TestScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        SQProgressBar(
            progress = 50,
        )


        Text(
            text = "QUIZ CONTENT",
            textAlign = TextAlign.Center,
            modifier = Modifier.height(500.dp)
        )



        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SQChip(
                text = "Hint",
                icon = Icons.Default.Home,
                foregroundColor = purple,
                backgroundColor = lightPurple,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        AnswerAlertPanel()


    }
}


@Preview(showBackground = true)
@Composable
private fun TestScreenPreview(){
    TestScreen()
}
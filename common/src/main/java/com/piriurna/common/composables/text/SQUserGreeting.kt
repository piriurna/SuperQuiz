package com.piriurna.common.composables.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.theme.SQStyle

@Composable
fun SQUserGreeting(
    modifier: Modifier = Modifier,
    userName : String,
    verticalArrangement : Arrangement.Vertical = Arrangement.spacedBy(12.dp)
) {
    Column(
        verticalArrangement= verticalArrangement,
        modifier = modifier
    ) {
        SQText(
            text = "\uD83D\uDC4B Hello, Dear ${userName}",
            color = Color.White
        )

        SQText(
            text = "What Do You Want To Improve?",
            color = Color.White,
            lineHeight = 48.sp,
            style = SQStyle.TextLato36
        )
    }
}

@Preview
@Composable
fun SQUserGreetingPreview() {
    SQUserGreeting(userName = "Franco Zalamena")
}
package com.piriurna.common.composables.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.R
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
            text = stringResource(id = R.string.hello_dear_username, userName),
            color = Color.White
        )

        SQText(
            text = stringResource(R.string.what_do_you_want_to_improve),
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
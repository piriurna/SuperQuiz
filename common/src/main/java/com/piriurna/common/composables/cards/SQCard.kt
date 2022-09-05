package com.piriurna.common.composables.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SQCard(
    modifier : Modifier = Modifier,
    radius : Dp = 30.dp,
    elevation : Dp = 0.dp,
    strokeColor : Color = Color.LightGray,
    strokeWidth : Dp = 1.dp,
    content: @Composable () -> Unit
) {

    Card(
        backgroundColor = Color.Transparent,
        modifier = modifier,
        shape = RoundedCornerShape(
            size = radius,
        ),
        elevation = elevation,
        border = BorderStroke(strokeWidth, strokeColor),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun SQCardPreview(){
    SQCard() {
        Text(text = "Test Card")
    }
}
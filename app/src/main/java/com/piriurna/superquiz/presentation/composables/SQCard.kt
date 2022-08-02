package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SQCard(
    modifier : Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Card(
        backgroundColor = Color.Transparent,
        modifier = modifier,
        shape = RoundedCornerShape(
            size = 30.dp,
        ),
        elevation = 0.dp,
        border = BorderStroke(1.dp, Color.LightGray),
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
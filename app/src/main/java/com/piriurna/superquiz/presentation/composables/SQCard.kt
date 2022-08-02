package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SQCard(
    modifier : Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Card(shape = RoundedCornerShape(
        size = 30.dp,
    ),
        elevation = 0.dp,
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = modifier,
        content = content
    )
}
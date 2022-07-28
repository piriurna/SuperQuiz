package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.lightOrange
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQProgressBar(
    progress: Float,
    foregroundColor: Color,
    backgroundColor: Color
) {
    LinearProgressIndicator(
        progress = progress,
        color = foregroundColor,
        backgroundColor = backgroundColor,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SQProgressBar(progress = 0.3f, foregroundColor = orange, backgroundColor = lightOrange)

    }
}
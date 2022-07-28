package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.purple


@Composable
fun SQViewPagerIndicatorDot(
    color: Color,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if(selected) {
            Surface(
                shape = RoundedCornerShape(15.dp),
                color = color,
                modifier = Modifier
                    .width(64.dp)
                    .height(4.dp)
            ) {

            }
        } else {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQViewPagerIndicatorDotPreview() {
    SQViewPagerIndicatorDot(color = purple, selected = true)
}
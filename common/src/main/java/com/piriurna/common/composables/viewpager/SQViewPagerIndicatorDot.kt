package com.piriurna.common.composables.viewpager

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.lightPurple
import com.piriurna.common.theme.purple


@Composable
fun SQViewPagerIndicatorDot(
    selectedColor: Color,
    unselectedColor: Color,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val width = animateDpAsState(targetValue = if (selected) 48.dp else 12.dp)

    val height = animateDpAsState(targetValue = if (selected) 8.dp else 12.dp)

    val color = animateColorAsState(targetValue = if(selected) selectedColor else unselectedColor)

    Box(modifier = modifier) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = color.value,
            modifier = Modifier
                .width(width = width.value)
                .height(height = height.value)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQViewPagerIndicatorDotPreview() {
    var selected by remember {
        mutableStateOf(true)
    }

    Column() {
        SQViewPagerIndicatorDot(
            modifier = Modifier.clickable {
                selected = !selected
            },
            selectedColor = purple,
            unselectedColor = lightPurple,
            selected = selected
        )
        SQViewPagerIndicatorDot(
            selectedColor = purple,
            unselectedColor = lightPurple,
            selected = !selected
        )
    }
}
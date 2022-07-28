package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQViewPagerIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                SQViewPagerIndicatorDot(color = selectedColor, selected = true)
            } else {
                SQViewPagerIndicatorDot(color = unSelectedColor, selected = false)
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQViewPagerIndicatorPreview() {
    SQViewPagerIndicator(9, 3, purple, lightPurple)
}
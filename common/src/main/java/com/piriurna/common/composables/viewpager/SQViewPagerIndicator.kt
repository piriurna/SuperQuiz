package com.piriurna.common.composables.viewpager

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.lightPurple
import com.piriurna.common.theme.purple

@Composable
fun SQViewPagerIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
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
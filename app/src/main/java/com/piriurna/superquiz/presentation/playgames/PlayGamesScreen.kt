package com.piriurna.superquiz.presentation.playgames

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.presentation.playgames.composables.CategoryCard
import com.piriurna.superquiz.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayGamesScreen() {
    Box(
        modifier = Modifier
            .background(brush = Brush.radialGradient(
                radius = 800f,
                center= Offset(x=250f, y = 800f),
                colors = listOf(
                    gradientInnerColor,
                    gradientCentralColor,
                    gradientOuterColor,
                )
            ))
            .fillMaxSize()
    ) {
        
        Column(
            verticalArrangement= Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 36.dp)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "\uD83D\uDC4B Hello, Dear",
                color = Color.White
            )
            
            Text(
                text = "What Do You Want To Improve?",
                fontSize = 36.sp,
                color = Color.White,
                lineHeight = 48.sp,
                fontWeight = FontWeight.W500
            )
        }
        
        Card(
            shape = RoundedCornerShape(
                topStartPercent = 10,
                topEndPercent = 10
            ),
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(12.dp),
                cells = GridCells.Fixed(2),
                content = {
                    val categories = getCategories()
                    items(categories.size) { index ->
                        CategoryCard(category = categories[index])
                    }
                }
            )
        }
    }
}


private fun getCategories() : List<Category> {
    return Category.mockCategoryList
}

@Preview(showBackground = true)
@Composable
private fun PlayGamesScreenPreview() {
    PlayGamesScreen()
}
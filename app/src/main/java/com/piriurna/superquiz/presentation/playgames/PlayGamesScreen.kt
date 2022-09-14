package com.piriurna.superquiz.presentation.playgames

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato36
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.presentation.navigation.HomeDestinationScreen
import com.piriurna.superquiz.presentation.playgames.composables.CategoryCard
import com.piriurna.superquiz.ui.theme.gradientCentralColor
import com.piriurna.superquiz.ui.theme.gradientInnerColor
import com.piriurna.superquiz.ui.theme.gradientOuterColor

@Composable
fun PlayGamesScreen(
    navController: NavController = rememberNavController()
) {
    val playGamesViewModel : PlayGamesViewModel = hiltViewModel()

    val state = playGamesViewModel.state.value

    BuildPlayGamesScreen(state = state, navController = navController, events = playGamesViewModel::onTriggerEvent)
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildPlayGamesScreen(
    state: PlayGamesState,
    events: ((PlayGamesEvents) -> Unit)? = null,
    navController: NavController,
) {
    SQScaffold(isLoading = state.isLoading) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.radialGradient(
                        radius = 800f,
                        center = Offset(x = 250f, y = 800f),
                        colors = listOf(
                            gradientInnerColor,
                            gradientCentralColor,
                            gradientOuterColor,
                        )
                    )
                )
                .fillMaxSize()
        ) {

            Column(
                verticalArrangement= Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 36.dp)
                    .padding(horizontal = 24.dp)
            ) {
                SQText(
                    text = "\uD83D\uDC4B Hello, Dear ${state.userName}",
                    color = Color.White
                )

                SQText(
                    text = "What Do You Want To Improve?",
                    color = Color.White,
                    lineHeight = 48.sp,
                    style = TextLato36
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
                        items(state.categories.size) { index ->
                            val category = state.categories[index]
                            CategoryCard(
                                modifier = Modifier,
                                onClick = {
                                    navController.navigate(route = HomeDestinationScreen.CategoryQuestions.route + "/${category.id}")
                                },
                                category = category
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayGamesScreenPreview() {
    BuildPlayGamesScreen(
        state = PlayGamesState(
            categories = Category.mockCategoryList
        ),
        navController = rememberNavController()
    )
}
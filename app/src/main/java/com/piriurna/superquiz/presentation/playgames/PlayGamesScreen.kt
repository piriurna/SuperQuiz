package com.piriurna.superquiz.presentation.playgames

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.piriurna.common.composables.scaffold.SQBottomSheetScaffold
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.text.SQUserGreeting
import com.piriurna.common.theme.gradientCentralColor
import com.piriurna.common.theme.gradientInnerColor
import com.piriurna.common.theme.gradientOuterColor
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.presentation.navigation.PlayGamesDestinations
import com.piriurna.superquiz.presentation.playgames.composables.CategoryCard

@Composable
fun PlayGamesScreen(
    navController: NavController = rememberNavController()
) {
    val playGamesViewModel : PlayGamesViewModel = hiltViewModel()

    val state = playGamesViewModel.state.collectAsState()

    BuildPlayGamesScreen(state = state.value, navController = navController, events = playGamesViewModel::onTriggerEvent)
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun BuildPlayGamesScreen(
    state: PlayGamesState,
    events: ((PlayGamesEvents) -> Unit)? = null,
    navController: NavController,
) {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)

    val swipeState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp

    var titleHeight by remember {
        mutableStateOf(0)
    }

    val titleTopPadding = 36.dp

    val titleVerticalArrangementSpacing = 12.dp

    val extraSpacing = 4.dp

    val titleSizeDp = with(LocalDensity.current) {
        titleHeight.toDp() + titleTopPadding + titleVerticalArrangementSpacing + extraSpacing
    }

    val cornerRadius = when(sheetState.progress.to){
        BottomSheetValue.Collapsed -> {
            (sheetState.progress.fraction * 10f).toInt()
        }
        BottomSheetValue.Expanded -> {
            10 - (sheetState.progress.fraction * 10f).toInt()
        }
    }

    SQScaffold(isLoading = state.isLoading, error = state.error) {
        SQBottomSheetScaffold(
            sheetPeekHeight = screenHeight.dp - titleSizeDp,
            sheetState = sheetState,
            sheetContent = {
            Card(
                shape = RoundedCornerShape(
                    topStartPercent = cornerRadius,
                    topEndPercent = cornerRadius
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                SwipeRefresh(
                    state = swipeState,
                    onRefresh = { events?.invoke(PlayGamesEvents.RefreshCategories) },
                    swipeEnabled = sheetState.isCollapsed
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
                                        navController.navigate(route = PlayGamesDestinations.Questions.withArgs(category.id))
                                    },
                                    category = category
                                )
                            }
                        }
                    )
                }
            }
        }) {
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

                SQUserGreeting(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = titleTopPadding)
                        .padding(horizontal = 24.dp)
                        .onSizeChanged {
                            titleHeight = it.height
                            Log.i("Playgames", it.toString())
                        },
                    verticalArrangement = Arrangement.spacedBy(titleVerticalArrangementSpacing),
                    userName = state.userName
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlayGamesScreenPreview() {
    val list = Category.mockCategoryList.toMutableList()
    list.addAll(Category.mockCategoryList)
    BuildPlayGamesScreen(
        state = PlayGamesState(
            categories = list
        ),
        navController = rememberNavController()
    )
}
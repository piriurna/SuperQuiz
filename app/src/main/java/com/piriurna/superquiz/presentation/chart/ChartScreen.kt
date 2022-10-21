package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.selector.SQSelector
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato16
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.SQStyle.TextLato22Bold
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.R
import com.piriurna.superquiz.mappers.getProgressChart
import com.piriurna.superquiz.mappers.toPieChartSections
import com.piriurna.superquiz.mappers.toSelectableItem
import com.piriurna.superquiz.mappers.toSelectableItems
import com.piriurna.superquiz.presentation.navigation.PlayGamesDestinations
import kotlinx.coroutines.launch
import java.lang.Integer.max
import kotlin.math.min

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChartScreen(
    navController: NavController
) {
    val viewModel : ChartViewModel = hiltViewModel()

    val state = viewModel.state.value

    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()

    BuildChartScreen(
        navController = navController,
        state = state,
        events = viewModel::onTriggerEvent,
        pagerState = pagerState,
        onSelectorNextClicked ={
            scope.launch {
                val pageTo = min(pagerState.currentPage + 1, pagerState.pageCount - 1)
                pagerState.animateScrollToPage(pageTo)
            }
        },
        onSelectorPreviousClicked = {
            scope.launch {
                val pageTo = max(pagerState.currentPage - 1, 0)
                pagerState.animateScrollToPage(pageTo)
            }
        }
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuildChartScreen(
    navController: NavController,
    state: ChartState,
    events : (ChartEvents) -> Unit,
    pagerState: PagerState,
    onSelectorNextClicked : () -> Unit,
    onSelectorPreviousClicked : () -> Unit
) {

    SQScaffold(isLoading = state.isLoading) {

        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if(state.categories.isNotEmpty()){
                SQSelector(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    item = state.categories[pagerState.currentPage].toSelectableItem(),
                    onNextPressed = {
                        onSelectorNextClicked()
                    },
                    onPreviousPressed = {
                        onSelectorPreviousClicked()
                    }
                )

                SQText(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = stringResource(R.string.these_are_your_numbers),
                    style = TextLato22Bold, color = Color.Black,
                )

                SQText(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = stringResource(R.string.here_you_can_check_your_progress_in_each_category),
                    style = TextLato16, color = Color.Black
                )

                HorizontalPager(
                    count = state.categories.size,
                    state = pagerState
                ) { index ->
                    val category = state.categories.getOrNull(index)

                    if(category != null) {
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryCompletionChartCard(
                                sections = category.getProgressChart(),
                                onPrimaryAction = {
                                    navController.navigate(PlayGamesDestinations.Questions.withArgs(category.id))
                                },
                                totalNumberOfQuestions = category.totalNumberOfQuestions,
                                numberOfAnsweredQuestions = category.getAnsweredQuestions(),
                                categoryName = category.name
                            )

                            SQText(
                                text = stringResource(R.string.statistics),
                                style = TextLato22
                            )

                            CategoryInformationChartCard(
                                sections = category.toPieChartSections(),
                                correctPercentage = category.getPercentageOfCorrectAnsweredAnswers()
                            )
                        }
                    }
                }
            }



        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    BuildChartScreen(
        rememberNavController(),
        ChartState(
            categories = Category.mockCategoryList
        ),
        pagerState = PagerState(0),
        onSelectorPreviousClicked = {},
        onSelectorNextClicked = {},
        events = {}
    )
}
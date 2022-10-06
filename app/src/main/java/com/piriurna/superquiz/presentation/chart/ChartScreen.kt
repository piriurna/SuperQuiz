package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.chart.SQPieChart
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.selector.SQSelector
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.superquiz.mappers.toPieChartSections
import com.piriurna.superquiz.mappers.toSelectableItem
import com.piriurna.superquiz.mappers.toSelectableItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChartScreen() {
    val viewModel : ChartViewModel = hiltViewModel()

    val state = viewModel.state.value

    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()
    SQScaffold(isLoading = state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SQSelector(
                items = state.categories.toSelectableItems(),
                onNextPressed = { index ->
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                onPreviousPressed = { index ->
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            HorizontalPager(count = state.categories.size, state = pagerState) { page ->

                val currentPageStatistics = state.categories.getOrElse(page) { null }

                currentPageStatistics?.let {
                    SQPieChart(sections = it.toPieChartSections(), pieSize = 500f)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    ChartScreen()
}
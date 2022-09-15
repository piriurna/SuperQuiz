package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.chart.SQPieChart
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.selector.SQSelector
import com.piriurna.superquiz.mappers.toPieChartSections
import com.piriurna.superquiz.mappers.toSelectableItem

@Composable
fun ChartScreen() {
//    val viewModel : ChartViewModel = hiltViewModel()

    val state = ChartState()

    SQScaffold(isLoading = state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            SQSelector(
                items = state.categories.toSelectableItem(),
                onItemChanged = { category ->
//                    viewModel.setSelectedCategory(category.id)
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
            if(state.questions.isNotEmpty())
                SQPieChart(sections = state.questions.toPieChartSections(), pieSize = 500f)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    ChartScreen()
}
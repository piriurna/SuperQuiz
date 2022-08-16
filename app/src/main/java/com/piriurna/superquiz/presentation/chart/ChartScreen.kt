package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.chart.SQPieChart
import com.piriurna.superquiz.mappers.toPieChartSections

@Composable
fun ChartScreen() {
    val viewModel : ChartViewModel = hiltViewModel()

    val state = viewModel.state.value


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.questions.isNotEmpty())
            SQPieChart(sections = state.questions.toPieChartSections())
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    ChartScreen()
}
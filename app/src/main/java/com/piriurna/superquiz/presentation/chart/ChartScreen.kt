package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.theme.SQStyle.TextLato
import com.piriurna.common.theme.SQStyle.TextLato16
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.SQStyle.TextLato22Bold
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.incompleteGray
import com.piriurna.common.theme.progressBlue
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.mappers.getProgressChart
import com.piriurna.superquiz.mappers.toPieChartSections

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChartScreen() {
    val viewModel : ChartViewModel = hiltViewModel()

    val state = viewModel.state.value

    BuildChartScreen(state = state, events = viewModel::onTriggerEvent)
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuildChartScreen(
    state: ChartState,
    events : (ChartEvents) -> Unit
) {
    val pagerState = rememberPagerState()

    val scope = rememberCoroutineScope()

    SQScaffold(isLoading = state.isLoading) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SQText(
                    text = "These are your numbers",
                    style = TextLato22Bold, color = Color.Black
                )

                SQText(
                    text = "Here you can check your progress in each category",
                    style = TextLato16, color = Color.Black
                )
            }

            CategoryCompletionChartCard(
                sections = state.categories[1].getProgressChart(),
                onPrimaryAction = {},
                totalNumberOfAnswers = state.categories[1].totalNumberOfQuestions,
                numberOfCorrectAnswers = state.categories[1].correctAnswers,
                categoryName = state.categories[1].name
            )

            SQText(
                text = "Statistics",
                style = TextLato22
            )

            CategoryInformationChartCard(
                sections = state.categories[1].toPieChartSections(),
                correctPercentage = state.categories[1].getPercentageOfCorrectAnswers()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    BuildChartScreen(
        ChartState(
            categories = Category.mockCategoryList
        )
    ) {}
}
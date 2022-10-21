package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.common.composables.chart.SQPieChart
import com.piriurna.common.composables.text.SQColorIndicatorText
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.incompleteGray
import com.piriurna.common.theme.primaryGreen
import com.piriurna.superquiz.R

@Composable
fun CategoryInformationChartCard(
    modifier: Modifier = Modifier,
    sections : List<PieChartSection>,
    incompleteColor : Color = incompleteGray,
    incompleteText : String = stringResource(R.string.not_answered),
    correctPercentage : Int
) {
    SQCard{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {

                SQPieChart(
                    sections = sections,
                    pieChartSize = 150.dp,
                    graphThickness = 24f,
                    centerTextTitle = "$correctPercentage%",
                    centerTextSubTitle = stringResource(R.string.correct)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                sections.forEach {
                    SQColorIndicatorText(color = it.color, text = it.label)
                }

                SQColorIndicatorText(color = incompleteColor, text = incompleteText)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryInformationChartCardPreview() {
    CategoryInformationChartCard(
        modifier = Modifier.fillMaxWidth(),
        sections = listOf(
            PieChartSection(
                60,
                primaryGreen,
                "Answered Correctly"
            ),
            PieChartSection(
                10,
                errorColor,
                "Answered Incorrectly"
            ),
        ),
        correctPercentage = 67
    )
}
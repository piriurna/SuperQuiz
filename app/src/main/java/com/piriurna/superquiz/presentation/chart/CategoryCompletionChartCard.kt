package com.piriurna.superquiz.presentation.chart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.common.composables.chart.SQPieChart
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.theme.SQStyle.TextLato35Bold
import com.piriurna.common.theme.SQStyle.TextLatoBold
import com.piriurna.common.theme.purple

@Composable
fun CategoryCompletionChartCard(
    modifier: Modifier = Modifier,
    sections : List<PieChartSection>,
    onPrimaryAction : () -> Unit,
    categoryName : String,
    numberOfAnsweredQuestions : Int,
    totalNumberOfQuestions : Int
) {
    SQCard{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column(
                Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SQText(text = categoryName)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Check Mark Icon",
                        colorFilter = ColorFilter.tint(purple)
                    )

                    SQText(text = "$numberOfAnsweredQuestions/$totalNumberOfQuestions", style = TextLato35Bold, color = purple)

                }

                val substitle = when {
                    (numberOfAnsweredQuestions.toFloat() / totalNumberOfQuestions.toFloat()) == 1f -> "Well Done! You can get even more questions."
                    (numberOfAnsweredQuestions.toFloat() / totalNumberOfQuestions.toFloat()) > 0.6f -> "Almost Finished! Keep it up!"
                    (numberOfAnsweredQuestions.toFloat() / totalNumberOfQuestions.toFloat()) == 0f -> "You could try this category!"
                    else -> {
                        "Focus! You can do it"
                    }
                }

                SQText(text = substitle, style = TextLatoBold)

                SQButton(
                    modifier = Modifier.width(130.dp),
                    backgroundColor = purple,
                    onClick = onPrimaryAction,
                    buttonText = "Questions"
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {

                SQPieChart(
                    sections = sections,
                    pieChartSize = 120.dp,
                    graphThickness = 26f,
                    centerTextTitle = "${numberOfAnsweredQuestions * 100 / totalNumberOfQuestions}%",
                    centerTextSubTitle = "Done"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryCompletionChartCardPreview() {
    CategoryCompletionChartCard(
        modifier = Modifier.fillMaxWidth(),
        sections = PieChartSection.getMockPieChartSections(),
        onPrimaryAction = {},
        "Expenses",
        numberOfAnsweredQuestions = 4,
        totalNumberOfQuestions = 6
    )
}
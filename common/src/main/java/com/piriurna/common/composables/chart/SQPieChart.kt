package com.piriurna.common.composables.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.lightPurple

@Composable
fun SQPieChart(
    modifier: Modifier = Modifier,
    sections: List<PieChartSection>,
    pieChartSize : Dp,
    graphThickness : Float,
    centerTextTitle : String? = null,
    centerTextSubTitle : String? = null
) {

    Box(
        modifier = modifier.size(pieChartSize),
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.size(pieChartSize * 0.9f)) {
            drawCircle(
                lightPurple,
                size.width / 2,
                style = Stroke(graphThickness)
            )

            var currentStartingAngle = -90f

            sections.forEach {
                val sectionAngle = ((it.percentage / 100f) * 360).toFloat()

                drawArc(
                    color = it.color,
                    startAngle = currentStartingAngle,
                    sweepAngle = sectionAngle,
                    useCenter = false,
                    style = Stroke(graphThickness, cap = StrokeCap.Round),
                )
                currentStartingAngle += sectionAngle
            }


        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            centerTextTitle?.let {
                SQText(text = it, style = TextLato27Bold)
            }
            centerTextSubTitle?.let {
                SQText(text = it, style = TextLato22)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SQPieChartPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SQPieChart(
            sections = PieChartSection.getMockPieChartSections(),
            pieChartSize = 350.dp,
            graphThickness = 35f,
            centerTextTitle = "67%",
            centerTextSubTitle = "Complete"
        )
    }
}
package com.piriurna.common.composables.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.models.PieChartSection
import com.piriurna.common.models.PieChartSectionArea

@Composable
fun SQPieChart(
    modifier: Modifier = Modifier,
    sections: List<PieChartSection>,
    pieSize : Float = 735F
) {

    val DEFAULT_SECTION_WIDTH by derivedStateOf { pieSize / 3F }

    val SELECTED_SECTION_WIDTH by derivedStateOf { pieSize / 2.5F }

    val totalPie by remember {
        mutableStateOf(sections.map {it.value}.sum())
    }

    val proportions by derivedStateOf {
        sections.map { it.value / totalPie }
    }

    val sweepAnglePercentage by derivedStateOf {
        proportions.map { 360 * it }
    }

    val progressSize = mutableListOf<Float>()
    progressSize.add(sweepAnglePercentage.first())

    for (x in 1 until sweepAnglePercentage.size)
        progressSize.add(sweepAnglePercentage[x] + progressSize[x - 1])

    val barAreas = sections.mapIndexed { index, pieChartSection ->
        PieChartSectionArea(
            index = index,
            data = pieChartSection,
            angle = progressSize[index]
        )
    }

    var selectedPosition by remember { mutableStateOf(0.0) }
    val selectedSection by remember(selectedPosition, barAreas) {
        derivedStateOf {
            barAreas.forEachIndexed { index, pieChartSectionArea ->
                val sweepSize = sweepAnglePercentage[index]
                val sweepStart = pieChartSectionArea.angle - sweepSize
                if(selectedPosition <= pieChartSectionArea.angle &&
                        selectedPosition >= sweepStart) {
                    return@derivedStateOf pieChartSectionArea
                }
            }

            return@derivedStateOf null
        }
    }

    val canvasSize = with(LocalDensity.current) {
        (pieSize * 1.5f).toDp()
    }

    Canvas(
        modifier = modifier
            .size(canvasSize)
            .startGesture(
                pieWidth = pieSize,
                onCompleted = {
                    selectedPosition = it
                }
            )
    ) {

        var startAngle = 0f

        sections.forEachIndexed { index, section ->

            val sectionWidth = if(selectedSection?.index == index) SELECTED_SECTION_WIDTH else DEFAULT_SECTION_WIDTH

            val padding = DEFAULT_SECTION_WIDTH * 1.5f

            val offset = padding/2F

            drawArc(
                color= section.color,
                size = Size(pieSize, pieSize),
                startAngle = startAngle,
                sweepAngle = sweepAnglePercentage[index],
                topLeft = Offset(offset, offset),
                useCenter = false,
                style = Stroke(
                    width = sectionWidth
                )
            )

            startAngle += sweepAnglePercentage[index]
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
            pieSize = 650f
        )
    }
}
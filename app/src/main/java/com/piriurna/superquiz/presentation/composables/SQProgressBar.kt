package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.presentation.composables.models.ChipModel
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorModel
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorText
import com.piriurna.superquiz.ui.theme.*

@Composable
fun SQProgressBar(
    modifier: Modifier = Modifier,
    progressIndicatorModel: ProgressIndicatorModel
) {

    val progressColor =
        if(progressIndicatorModel.isCompleted())
            progressIndicatorModel.completedColor
        else
            progressIndicatorModel.progressColor

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier= Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            progressIndicatorModel.progressIndicatorText?.let {
                Text(
                    text = it.text,
                    color = if(progressIndicatorModel.isZero()) it.incompleteColor else it.progressColor
                )
            }

            progressIndicatorModel.chipModel?.let {
                SQChip(
                    text = it.text,
                    icon = it.icon,
                    foregroundColor = it.foregroundColor,
                    backgroundColor = it.backgroundColor
                )
            }
        }

        LinearProgressIndicator(
            progress = progressIndicatorModel.progress/100f,
            color = progressColor,
            backgroundColor = progressIndicatorModel.backgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(shape = RoundedCornerShape(30.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SQProgressBar(
            progressIndicatorModel = ProgressIndicatorModel(
                progress = 30,
                progressIndicatorText = ProgressIndicatorText.FractionText(30),
                chipModel = ChipModel(
                    icon = Icons.Default.Home,
                    text = "3min 55s",
                    backgroundColor = lightOrange,
                    foregroundColor = orange
                )
            ),
        )

    }
}
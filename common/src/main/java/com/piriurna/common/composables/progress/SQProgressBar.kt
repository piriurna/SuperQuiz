package com.piriurna.common.composables.progress

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.chip.SQChip
import com.piriurna.common.theme.*

@Composable
fun SQProgressBar(
    modifier: Modifier = Modifier,
    progress : Int = 0,
    barProgressColor: Color = progressBlue,
    barBackgroundColor: Color = incompleteGray,
    barCompletedColor: Color = primaryGreen,
    textIncompleteColor: Color = Color.Gray,
    textProgressColor: Color = Color.Black,
    chipForegroundColor: Color = progressBlue,
    chipBackgroundColor: Color = incompleteGray,
    chipIcon : ImageVector? = null,
    chipText : String? = null,
    percentageText: String? = null
) {

    val progressColor =
        if(progress == 100)
            barCompletedColor
        else
            barProgressColor

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier= Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            percentageText?.let {
                Text(
                    text = it,
                    color = if(progress == 0) textIncompleteColor else textProgressColor
                )
            }

            chipIcon?.let { chipIcon ->
                chipText?.let { chipText ->
                    SQChip(
                        text = chipText,
                        icon = chipIcon,
                        foregroundColor = chipForegroundColor,
                        backgroundColor = chipBackgroundColor
                    )
                }
            }
        }

        LinearProgressIndicator(
            progress = progress/100f,
            color = progressColor,
            backgroundColor = barBackgroundColor,
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
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        SQProgressBar(
            progress = 50,
            barBackgroundColor = incompleteGray,
            barCompletedColor = primaryGreen,
            barProgressColor = progressBlue,
            chipBackgroundColor = lightOrange,
            chipForegroundColor = orange,
            chipIcon = Icons.Default.Home,
            chipText = "5min 55s",
            percentageText = "3/10"
        )

        //TODO: Melhorar para nao precisar definir 2 progressos
        SQProgressBar(
            progress = 0,
            barBackgroundColor = incompleteGray,
            barCompletedColor = primaryGreen,
            barProgressColor = progressBlue,
            percentageText = "You completed ${0}%"
        )

        SQProgressBar(
            progress = 100,
        )

    }
}
package com.piriurna.superquiz.presentation.playgames.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.superquiz.presentation.composables.SQProgressBar
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorModel
import com.piriurna.superquiz.ui.theme.*

@Composable
fun ProgressWithText(
    modifier: Modifier = Modifier,
    foregroundColor : Color = progressBlue,
    backgroundColor: Color = incompleteGray,
    completionPercentage : Int
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ){
        Text(
            text = "You Completed ${completionPercentage}%",
            fontSize = 12.sp,
            color = if(completionPercentage == 0) Color.Gray else Color.Black
        )
        SQProgressBar(
            progressIndicatorModel = ProgressIndicatorModel(
                progress = completionPercentage,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressWithTextPreview() {
    ProgressWithText(completionPercentage = 0)
}
package com.piriurna.common.composables.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.theme.primaryGreen
import com.piriurna.common.theme.purple

@Composable
fun SQColorIndicatorText(
    modifier: Modifier = Modifier,
    color: Color,
    text : String
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle_outlined),
            contentDescription = "Legend indicator",
            colorFilter = ColorFilter.tint(color)
        )

        SQText(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun SQColorIndicatorTextPreview() {
    SQColorIndicatorText(
        color = primaryGreen,
        text = "Answered Correctly"
    )
}
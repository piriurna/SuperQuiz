package com.piriurna.common.composables.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.theme.primaryGreen

@Composable
fun SQBadgeText(
    modifier: Modifier = Modifier,
    icon : ImageVector,
    text: String,
    foregroundColor : Color = Color.Black
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.icon),
            tint = foregroundColor
        )

        SQText(
            text = text,
            color = foregroundColor,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SQBadgeTextPreview() {
    SQBadgeText(
        icon = Icons.Default.Done,
        text = "Correct Answer",
        foregroundColor = primaryGreen
    )
}
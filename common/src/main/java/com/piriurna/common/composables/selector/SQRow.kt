package com.piriurna.common.composables.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato22

@Composable
fun SQRow(
    modifier: Modifier = Modifier,
    label : String,
    onClick : () -> Unit,
    checkable : Boolean = false,
    isChecked : Boolean = false,
    onCheckedChange : (Boolean) -> Unit = {},
    startIcon : ImageVector? = null
) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .clickable {
                if (!checkable) onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            startIcon?.let {
                Image(imageVector = it, contentDescription = "$label Start Icon")
            }

            SQText(
                text = label,
                style = TextLato22
            )
        }

        if(checkable)
            SQSwitch(checked = isChecked, onCheckedChange = onCheckedChange)
        else
            Image(imageVector = Icons.Default.ArrowForward, contentDescription = "$label End Icon")
    }
}


@Preview(showBackground = true)
@Composable
fun SQRowPreview() {
    Column() {
        SQRow(
            label = "Teste",
            onClick = {},
            startIcon = Icons.Default.Face,
        )
    }
}
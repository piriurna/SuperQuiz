package com.piriurna.common.composables.chip

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.lightOrange
import com.piriurna.common.theme.lightPurple
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.purple

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SQChip(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector,
    backgroundColor: Color = Color.Red,
    foregroundColor: Color = Color.White,
    onClick : () -> Unit = {}
) {
    print(Icons.Default.AccountBox.defaultHeight)
    Surface(
        shape = RoundedCornerShape(40.dp),
        color = backgroundColor,
        modifier = modifier,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                    .padding(vertical = 4.dp)
                    .padding(end = 12.dp, start = 8.dp)
        ) {

            Icon(
                imageVector = icon,
                tint = foregroundColor,
                contentDescription = "chip icon",
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                color = foregroundColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SQChip(
            text = "3min 55s",
            icon = Icons.Default.AccountBox,
            backgroundColor = lightOrange,
            foregroundColor = orange,
        )

        SQChip(
            text = "Hints",
            icon = Icons.Default.Home,
            backgroundColor = lightPurple,
            foregroundColor = purple
        )
    }
}
package com.piriurna.common.composables.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SQChip(
    text: String,
    icon: ImageVector,
    backgroundColor: Color = Color.Red,
    foregroundColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Surface(
//        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
//        elevation = 8.dp,
        shape = RoundedCornerShape(40.dp),
        color = backgroundColor,
        modifier = modifier
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
                contentDescription = "chip icon"
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
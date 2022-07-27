package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.lightOrange
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQChip(
    text: String,
    icon: ImageVector,
    backgroundColor: Color = Color.Red,
    foregroundColor: Color = Color.White
) {
    Surface(
//        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
//        elevation = 8.dp,
        shape = RoundedCornerShape(40.dp),
        color = backgroundColor
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 12.dp, start = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(end = 8.dp),
            ) {
                Icon(
                    imageVector = icon,
                    tint = foregroundColor,
                    contentDescription = "chip icon"
                )
            }
            Column {
                Text(
                    text = text,
                    color = foregroundColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SQChip(
            text = "3min 55s",
            icon = Icons.Default.Clear,
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
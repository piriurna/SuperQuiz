package com.piriurna.common.composables.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.purple

@Composable
fun RowScope.SQNavigationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected : Boolean = false,
    text: String,
    selectedColor : Color,
    unselectedColor : Color = Color.LightGray,
    onClick: () -> Unit = {}
) {
    val iconColor = if(selected) selectedColor else unselectedColor
    val textColor = if(selected) Color.Black else Color.LightGray

    Box(
        modifier = modifier
            .weight(1f)
            .align(Alignment.Bottom)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .clickable { onClick.invoke() }
                .align(Alignment.Center),
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = icon,
                contentDescription = "Item Icon",
                tint = iconColor
            )

            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.Black
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SQNavigationItemPreview() {
    Row(Modifier.fillMaxSize()) {
        SQNavigationItem(
            icon = Icons.Default.Home,
            text = "Play Games",
            selectedColor = purple
        )

        SQNavigationItem(
            icon = Icons.Default.Menu,
            text = "Profile",
            selected = true,
            selectedColor = orange
        )
    }
}
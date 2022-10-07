package com.piriurna.common.composables.navigation

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato12
import com.piriurna.common.theme.SQStyle.TextLatoBold
import com.piriurna.common.theme.SQStyle.TextLatoBold12
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
    onClick: () -> Unit = {},
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val iconColor = if(selected) selectedColor else unselectedColor
    val textColor = if(selected) Color.Black else Color.LightGray

    val top by animateDpAsState(
        targetValue = if (selected) 56.dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
    )

    Box(
        modifier = modifier
            .requiredHeight(56.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,

    ) {
        Icon(
            imageVector = icon,
            tint = iconColor,
            contentDescription = null,
            modifier = Modifier
                .height(56.dp)
                .width(26.dp)
                .offset(y = 56.dp - top)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(56.dp)
                .offset(y = top)

        ) {
            SQText(
                text = text,
                style = TextLatoBold,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQNavigationItemPreview() {
    var selected by remember {
        mutableStateOf(true)
    }
    Row() {
        SQNavigationItem(
            icon = Icons.Default.Menu,
            text = "Profile",
            selected = selected,
            selectedColor = orange,
            onClick = {
                selected = !selected
            }
        )
    }
}
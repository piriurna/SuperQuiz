package com.piriurna.common.composables.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.primaryText

@Composable
fun SQAppBarIcon(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    icon : ImageVector,
    iconSize : Dp = 12.dp,
    iconColor: Color = MaterialTheme.colors.primaryText,
    iconBackgroundColor : Color = Color.White
) {
    IconButton(modifier = modifier, onClick = onClick) {
        Box(
            modifier = Modifier
                .background(shape = CircleShape, color = iconBackgroundColor)
        ) {
            Icon(
                modifier = Modifier.padding(16.dp).size(iconSize),
                imageVector = icon,
                tint = iconColor,
                contentDescription = "Back Button"
            )
        }
    }
}

@Preview
@Composable
fun SQAppBarIconPreview() {
    SQAppBarIcon(
        onClick = {},
        icon = Icons.Default.ArrowBack
    )
}
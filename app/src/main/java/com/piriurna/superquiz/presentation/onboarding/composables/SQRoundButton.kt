package com.piriurna.superquiz.presentation.onboarding.composables

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQRoundButton(
    icon : ImageVector = Icons.Default.ArrowForward,
    imageColor: Color,
    buttonColor : Color,
    onClickListener: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickListener,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        modifier = modifier
            .size(80.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Button Icon",
                colorFilter = ColorFilter.tint(imageColor),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQRoundButtonPreview(){
    Box(modifier = Modifier.fillMaxSize()) {
        SQRoundButton(buttonColor = purple, imageColor = purple, onClickListener = {})
    }
}
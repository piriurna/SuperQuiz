package com.piriurna.superquiz.presentation.onboarding.composables

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQRoundButton(
    primaryColor : Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor),
        modifier = modifier
            .width(80.dp)
            .height(80.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .wrapContentWidth()
                .wrapContentHeight()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Button Icon",
                colorFilter = ColorFilter.tint(primaryColor),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SQRoundButtonPreview(){
    Box(modifier = Modifier.fillMaxSize()) {
        SQRoundButton(primaryColor = purple)
    }
}
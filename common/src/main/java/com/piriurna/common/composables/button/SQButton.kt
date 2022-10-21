package com.piriurna.common.composables.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.common.theme.backgroundGreen
import com.piriurna.common.theme.primaryGreen

@Composable
fun SQButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick : () -> Unit,
    backgroundColor : Color = primaryGreen,
    foregroundColor: Color = backgroundGreen,
    buttonText: String,
    textColor : Color = Color.White,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = foregroundColor),
        enabled = enabled,
        modifier = modifier
            .height(45.dp)
    ) {
        SQText(
            text = buttonText,
            color = textColor,
            style = SQStyle.TextLatoBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SQButtonPreview() {
    SQButton(onClick = {}, buttonText = "Get Started")
}
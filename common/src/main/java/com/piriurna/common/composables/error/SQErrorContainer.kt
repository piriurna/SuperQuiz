package com.piriurna.common.composables.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.common.theme.SQStyle.TextLato18
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.purple

@Composable
fun SQErrorContainer(
    modifier : Modifier = Modifier,
    imageResource : Int,
    title: String,
    subtitle : String,
    hasButton : Boolean,
    buttonText : String = "",
    buttonBackgroundColor : Color = purple,
    buttonTextColor : Color = Color.White,
    buttonModifier: Modifier = Modifier.fillMaxWidth(0.5f),
    buttonOnClick : () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = imageResource),
            contentDescription = "Error Image"
        )

        SQText(text = title, style = TextLato27Bold)

        SQText(text = subtitle, style = TextLato18)

        if(hasButton)
            SQButton(modifier = buttonModifier, onClick = buttonOnClick, buttonText = buttonText, textColor = buttonTextColor, backgroundColor = buttonBackgroundColor, foregroundColor = buttonTextColor)
    }
}

@Preview(showBackground = true)
@Composable
fun SQErrorContainerPreview() {
    SQErrorContainer(
        imageResource = R.drawable.ic_disconnected,
        title = "Error!",
        subtitle = "No Internet Connection",
        buttonText = "RETRY",
        buttonBackgroundColor = purple,
        hasButton = true,
        buttonOnClick = {}
    )
}
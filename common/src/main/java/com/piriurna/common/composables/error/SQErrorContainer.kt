package com.piriurna.common.composables.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato18
import com.piriurna.common.theme.SQStyle.TextLato27Bold
import com.piriurna.common.theme.errorColor

@Composable
fun SQErrorContainer(
    modifier : Modifier = Modifier,
    imageResource : Int,
    title: String,
    subtitle : String,
    button : @Composable (() -> Unit)? = null
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

        button?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
fun SQErrorContainerPreview() {
    SQErrorContainer(
        imageResource = R.drawable.ic_disconnected,
        title = "Error!",
        subtitle = "No Internet Connection",
        button = {
            SQButton(modifier = Modifier.width(150.dp), onClick = { /*TODO*/ }, buttonText = "RETRY", backgroundColor = errorColor)
        }
    )
}
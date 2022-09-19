package com.piriurna.common.composables.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.common.theme.primaryText

@Composable
fun SQAlertDialog(
    title : String? = null,
    description : String,
    dialogState: Boolean = true,
    onDialogStateChange: ((Boolean) -> Unit)? = null,
    laterLabel : String? = null,
    laterClick : (() -> Unit)? = null,
    okLabel: String,
    okClick : () -> Unit,
    themeColor : Color,
    textColor : Color = MaterialTheme.colors.primaryText,
    buttonTextColor : Color = Color.White,
    dialogProperties: DialogProperties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
) {

    if (dialogState) {
        Dialog(
            onDismissRequest = {
                onDialogStateChange?.invoke(false)
                laterClick?.invoke()
            },
            properties = dialogProperties
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background, shape = RoundedCornerShape(5))
                    .clip(RoundedCornerShape(5)),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(imageVector = Icons.Default.Refresh, contentDescription = "Refresh", modifier = Modifier.size(90.dp), colorFilter = ColorFilter.tint(themeColor.copy(alpha = 0.5f)))

                    title?.let {
                        SQText(text = it, style = SQStyle.TextLato27Bold, color = textColor)
                    }

                    SQText(
                        text = description,
                        textAlign = TextAlign.Center,
                        color = textColor
                    )
                }

                Row(
                    modifier = Modifier
                        .background(themeColor.copy(alpha = 0.5f))
                        .fillMaxWidth()
                ) {
                    laterLabel?.let {
                        Button(onClick = { laterClick?.invoke() }, modifier = Modifier.fillMaxWidth(0.5f), elevation = ButtonDefaults.elevation(0.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.White)){
                            SQText(it, color = buttonTextColor)
                        }
                    }
                    Button(onClick = { okClick() }, modifier = Modifier.fillMaxWidth(), elevation = ButtonDefaults.elevation(0.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.White)){
                        SQText(okLabel, color = buttonTextColor)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SQAlertDialogPreview() {
    Column(Modifier.fillMaxSize()) {
        SQAlertDialog(title = "Get Updates", description="Allow Permission to send you notifications when new art styles added" ,themeColor = Color.Red.copy(alpha = 0.5f), okLabel = "Allow", laterLabel = "Not Now", okClick = {}, laterClick = {})
    }
}
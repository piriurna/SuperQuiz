package com.piriurna.common.composables.text

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
fun SQText(
    text: String,
    modifier: Modifier = Modifier,
//    color: Color = MaterialTheme.colors.primaryText,
    textAlign: TextAlign? = null,
//    style: TextStyle = FGStyle.TextAlbertSans,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {

    Text(
        text = text,
        modifier = modifier,
//        color = color,
        textAlign = textAlign,
//        style = style,
        letterSpacing = letterSpacing,
        overflow = overflow,
        maxLines = maxLines
    )
}

@Preview(showBackground = true)
@Composable
private fun FGTextPreview() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SQText(text = "Teste 1")
        SQText(text = "Teste 2")
        SQText(text = "Teste 3")
        SQText(text = "Teste 4")
    }
}

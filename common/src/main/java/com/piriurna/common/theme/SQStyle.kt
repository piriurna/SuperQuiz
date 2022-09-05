package com.piriurna.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.piriurna.common.R


object SQStyle {
    private val LatoFont = FontFamily(
        Font(R.font.lato_regular),
        Font(R.font.lato_thin, FontWeight.W300),
        Font(R.font.lato_bold, FontWeight.Bold)
    )

    val TextLato = TextStyle(
        fontFamily = LatoFont,
        fontSize = 14.sp
    )

    val TextLato12 = TextLato.copy(
        fontSize = 12.sp
    )

    val TextLatoBold = TextLato.copy(
        fontWeight = FontWeight.Bold
    )

    val TextLatoThin = TextLato.copy(
        fontWeight = FontWeight.W300
    )

    val TextLato22 = TextLato.copy(
        fontSize = 22.sp
    )

    val TextLato35 = TextLato.copy(
        fontSize = 35.sp
    )
}

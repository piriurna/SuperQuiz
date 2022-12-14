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
        Font(R.font.lato_light, FontWeight.W300),
        Font(R.font.lato_bold, FontWeight.Bold)
    )

    val TextLato = TextStyle(
        fontFamily = LatoFont,
        fontSize = 14.sp
    )

    val TextLatoBold = TextLato.copy(
        fontWeight = FontWeight.Bold
    )

    val TextLato12 = TextLato.copy(
        fontSize = 12.sp
    )

    val TextLatoBold12 = TextLatoBold.copy(
        fontSize = 12.sp
    )


    val TextLatoThin = TextLato.copy(
        fontWeight = FontWeight.W300
    )

    val TextLato18 = TextLato.copy(
        fontSize = 18.sp
    )

    val TextLatoThin18 = TextLatoThin.copy(
        fontSize = 18.sp
    )

    val TextLato22 = TextLato.copy(
        fontSize = 22.sp
    )

    val TextLato16 = TextLato.copy(
        fontSize = 16.sp
    )

    val TextLato22Bold = TextLatoBold.copy(
        fontSize = 22.sp
    )

    val TextLatoBold20 = TextLatoBold.copy(
        fontSize = 20.sp
    )

    val TextLato35 = TextLato.copy(
        fontSize = 35.sp
    )

    val TextLato36 = TextLato.copy(
        fontSize = 36.sp
    )
    val TextLato27 = TextLato.copy(
        fontSize = 27.sp
    )

    val TextLato27Bold = TextLatoBold.copy(
        fontSize = 27.sp
    )

    val TextLato35Bold = TextLatoBold.copy(
        fontSize = 35.sp
    )

    val TextLatoBold24 = TextLatoBold.copy(
        fontSize = 24.sp,
    )
}

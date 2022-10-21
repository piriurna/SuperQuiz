package com.piriurna.common.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


@get:Composable
val Colors.secondaryBackground: Color
    get() = if (isLight) Color(0xffffffff) else Color(0xff252525)

@get:Composable
val Colors.primaryText: Color
    get() = if (isLight) Color(0xff616161) else Color(0xfff3f5fb)


val purple = Color(74, 21, 173)
val lightPurple = Color(74, 21, 173, alpha = 26)

val orange = Color(red = 254, green = 177, blue = 87)
val lightOrange = Color(red = 254, green = 177, blue = 87, alpha = 26)

val primaryBlue = Color(red = 47, green = 204, blue = 237)
val backgroundBlue = Color(red = 172, green = 232, blue = 243)

val primaryGreen = Color(red = 54, green = 200, blue = 133)
val backgroundGreen = Color(red = 50, green = 182, blue = 122, alpha = 26)


val progressBlue = Color(red = 0, green = 101, blue = 253)


val incompleteGray = Color(red = 237, green = 234, blue = 255)

val errorColor = Color(192, 57, 43)

val gradientInnerColor = Color(146, 68, 248)
val gradientCentralColor = Color(138, 64, 249)
val gradientOuterColor = Color(77, 40, 218)
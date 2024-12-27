package com.loopcat.helper.ui.theme

import androidx.compose.ui.graphics.Color

sealed class HelperColor(
    val main: Color,
    val main50: Color,
    val main100: Color,

    val gray000: Color,
    val gray100: Color,
    val gray200: Color,
    val gray300: Color,
    val gray400: Color,
    val gray500: Color,
    val gray600: Color,
    val gray700: Color,
    val gray800: Color,
    val gray900: Color,

    val red: Color,
    val blue: Color,
    val dialogBlue: Color,
    val black: Color,
    val white: Color
) {
    data object Light: HelperColor(
        main = Color(0xFFFF9900),
        main50 = Color(0xFFFFE2B7),
        main100 = Color(0xFFFFBD5A),

        gray000 = Color(0xFFFDFDFD),
        gray100 = Color(0xFFEBEBEB),
        gray200 = Color(0xFFD3D3D3),
        gray300 = Color(0xFFBEBEBE),
        gray400 = Color(0xFFA7A7A7),
        gray500 = Color(0xFF929292),
        gray600 = Color(0xFF7C7C7C),
        gray700 = Color(0xFF505050),
        gray800 = Color(0xFF343434),
        gray900 = Color(0xFF121212),

        red = Color(0xFFEA2E33),
        blue = Color(0xFF2E41EA),
        dialogBlue = Color(0xFF5EB3F7),
        black = Color(0xFF000000),
        white = Color(0xFFFFFFFF)
    )
}
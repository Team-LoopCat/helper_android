package com.loopcat.helper.ui.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.Red

@Composable
fun AuthTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.ExtraBold,
            color = Black,
            fontSize = 30.sp
        )
    )
}

@Composable
fun AuthErrorMessage(
    modifier: Modifier = Modifier,
    errorType: AuthErrorType
) {
    Text(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 4.dp,
                top = 2.dp
            ),
        text = stringResource(id = authErrorMessage(errorType)),
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Red
        )
    )
}
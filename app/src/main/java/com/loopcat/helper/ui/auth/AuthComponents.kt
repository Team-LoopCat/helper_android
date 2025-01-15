package com.loopcat.helper.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.InputPlaceHolder
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Main100
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.Red
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.singleClickEvent

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

@Composable
private fun SmallInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    
    Box(
        modifier = modifier
            .fillMaxWidth(
                fraction = 0.73f
            )
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isFocused) Main else Gray300,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                start = 18.dp,
                end = 18.dp,
                top = 16.dp,
                bottom = 16.dp
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        BasicTextField(
            modifier = modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(),
            value = input,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Black
            ),
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if (input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
    }
}

@Composable
private fun SmallButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonText: String,
    onClick: () -> Unit
) {
    singleClickEvent { singleEvent ->
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                singleEvent.event {
                    onClick()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                disabledContentColor = White,
                contentColor = White,
                disabledContainerColor = Gray300,
                containerColor = Main100
            ),
            enabled = enable,
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 14.dp
            )
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = White
                )
            )
        }
    }
}

@Composable
fun SmallInputWithButton(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    enable: Boolean,
    buttonText: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallInput(
            input = input,
            hint = hint,
            onValueChange = onValueChange
        )
        Spacer(modifier = modifier.width(6.dp))
        SmallButton(
            enable = enable,
            buttonText = buttonText,
            onClick = onClick
        )
    }
}
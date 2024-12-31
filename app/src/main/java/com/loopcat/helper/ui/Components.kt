package com.loopcat.helper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

@Composable
fun HelperButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = 30.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            disabledContentColor = White,
            disabledContainerColor = Gray300,
            contentColor = White,
            containerColor = Main
        ),
        enabled = enable
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                color = White,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
    }
}

@Composable
fun InputPlaceHolder(
    modifier: Modifier = Modifier,
    hint: String
) {
    Text(
        text = hint,
        style = TextStyle(
            color = Gray400,
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    )
}

@Composable
fun HelperInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
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
                top = 14.dp,
                bottom = 14.dp
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                color = Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if(input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
    }
}
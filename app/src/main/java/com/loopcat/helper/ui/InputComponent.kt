package com.loopcat.helper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

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

@Composable
fun HelperPasswordInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isShowPassword by remember { mutableStateOf(false) }
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
                .padding(
                    end = 24.dp
                )
                .fillMaxWidth(),
            value = input,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            cursorBrush = SolidColor(Main),
            textStyle = TextStyle(
                color = Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                if(input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
        IconButton(
            modifier = modifier
                .align(Alignment.CenterEnd),
            onClick = {
                isShowPassword = !isShowPassword
            }
        ) {
            val passwordIcon = if(isShowPassword) R.drawable.icon_showpassword else R.drawable.icon_password
            val contentDescription = if(isShowPassword) "Hide Password Icon" else "Show Password Icon"

            Icon(
                modifier = modifier.size(20.dp),
                painter = painterResource(id = passwordIcon),
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun HelperContentInput(
    modifier: Modifier = Modifier,
    input: String,
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
            .heightIn(
                min = 160.dp
            )
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
                    InputPlaceHolder(hint = stringResource(id = R.string.content_input_hint))
                }
                innerTextField()
            }
        )
    }
}
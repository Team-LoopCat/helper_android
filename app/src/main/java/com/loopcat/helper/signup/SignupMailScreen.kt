package com.loopcat.helper.signup

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperInput
import com.loopcat.helper.ui.InputPlaceHolder
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.AuthTitle
import com.loopcat.helper.ui.auth.SmallInputWithButton
import com.loopcat.helper.ui.auth.checkRegex
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray600
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner
import com.loopcat.helper.ui.utills.singleClickEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.sin

@Composable
fun SignupMailScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    var mail by remember { mutableStateOf("") }
    var smallButtonEnable by remember { mutableStateOf(false) }
    var mailError by remember { mutableStateOf(AuthErrorType.NONE) }

    var code by remember { mutableStateOf("") }
    var codeTime by remember { mutableIntStateOf(0) }
    var codeError by remember { mutableStateOf(AuthErrorType.NONE) }

    var buttonEnable by remember { mutableStateOf(false) }

    var sendMail = ""

    if (code.isNotEmpty()) {
        buttonEnable = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        Column(
            modifier = modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.height(140.dp))
            AuthTitle(text = stringResource(id = R.string.signup))
            Spacer(modifier = modifier.height(60.dp))
            SmallInputWithButton(
                input = mail,
                hint = stringResource(id = R.string.signup_mail),
                enable = smallButtonEnable,
                buttonText = stringResource(id = R.string.signup_certify),
                onValueChange = { input ->
                    mail = input
                    if (!checkRegex(AuthRegexType.MAIL, mail)) {
                        mailError = AuthErrorType.MAIL_REGEX
                        smallButtonEnable = false
                    } else {
                        mailError = AuthErrorType.NONE
                        smallButtonEnable = true
                    }
                },
                onClick = {
                    sendMail = mail
                    codeTime = 300
                    scope.launch {
                        while (codeTime > 0) {
                            delay(1000L)
                            codeTime--
                        }
                    }
                }
            )
            AuthErrorMessage(errorType = mailError)
            CodeInput(
                input = code,
                hint = stringResource(id = R.string.signup_code),
                timeCount = codeTime,
                onValueChange = { newInput ->
                    code = newInput
                }
            )
            AuthErrorMessage(errorType = codeError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable,
            buttonText = stringResource(id = R.string.signup_complete_certify),
            onClick = {
                // 인증 코드가 일치하는지 확인
                if (sendMail != mail) {
                    codeError = AuthErrorType.MAIL_MODIFY
                } else if (codeTime == 0) {
                    codeError = AuthErrorType.CODE_TIMEOUT
                }
            }
        )
    }
}

@Composable
private fun CodeInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    timeCount: Int,
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
                end = 14.dp,
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                color = Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if (input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
        Text(
            modifier = modifier.align(Alignment.CenterEnd),
            text = formatTimer(timeCount),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Gray600
            )
        )
    }
}

@SuppressLint("DefaultLocale")
private fun formatTimer(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%01d:%02d", minutes, remainingSeconds)
}
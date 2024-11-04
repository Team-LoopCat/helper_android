package com.loopcat.helper.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.addFocusCleaner
import com.loopcat.helper.auth.AuthErrorMessage
import com.loopcat.helper.auth.AuthErrorType
import com.loopcat.helper.auth.AuthInput
import com.loopcat.helper.auth.AuthInputPlaceholder
import com.loopcat.helper.auth.AuthTitle
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

const val NAVIGATION_SIGNUP_MAIL = "signupMail"

@Preview
@Composable
fun SignupMailScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    var mail by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(false) }

    var isSendMail by remember { mutableStateOf(false) }
    var mailError by remember { mutableStateOf(AuthErrorType.NONE) }
    var codeError by remember { mutableStateOf(AuthErrorType.NONE) }

    if (!isSendMail && mail.isNotEmpty()) {
        buttonEnabled = true
    }
    if (isSendMail && code.isNotEmpty()) {
        buttonEnabled = true
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
            AuthTitle(title = stringResource(id = R.string.signup))
            Spacer(modifier = modifier.height(50.dp))
            MailInput(
                input = mail,
                isSendMail = isSendMail,
                onValueChange = { newMail ->
                    mail = newMail
                }
            )
            AuthErrorMessage(errorType = mailError)
            if (isSendMail) {
                MailCodeInput(
                    code = code,
                    onValueChange = { newCode ->
                        code = newCode
                    }
                )
                AuthErrorMessage(errorType = codeError)
            }
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnabled,
            buttonText = stringResource(
                id = if (isSendMail) R.string.signup_next else R.string.signup_confirm
            ),
            onClick = {
                if (isSendMail) {
                    // 코드 인증
                } else {
                    // 메일 중복 확인
                    isSendMail = true
                    buttonEnabled = false
                }
            }
        )
    }
}

@Composable
fun MailInput(
    modifier: Modifier = Modifier,
    input: String,
    isSendMail: Boolean,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier
                .padding(
                    start = 24.dp,
                    end = 8.dp
                )
                .fillMaxWidth(fraction = 0.66f),
            value = input,
            onValueChange = onValueChange,
            placeholder = {
                AuthInputPlaceholder(hint = stringResource(id = R.string.signup_mail))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = White,
                focusedContainerColor = White,
                unfocusedBorderColor = Gray300,
                focusedBorderColor = Main,
                cursorColor = Main
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                color = Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            readOnly = isSendMail
        )
        Text(
            text = stringResource(id = R.string.signup_mail_address),
            style = TextStyle(
                color = Black,
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )
    }
}

@Composable
fun MailCodeInput(
    modifier: Modifier = Modifier,
    code: String,
    onValueChange: (String) -> Unit
) {
    AuthInput(
        input = code, 
        hint = stringResource(id = R.string.signup_code),
        onValueChange = onValueChange
    )
}
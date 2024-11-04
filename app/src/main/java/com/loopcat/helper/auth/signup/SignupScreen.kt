package com.loopcat.helper.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loopcat.helper.R
import com.loopcat.helper.addFocusCleaner
import com.loopcat.helper.auth.AuthErrorMessage
import com.loopcat.helper.auth.AuthErrorType
import com.loopcat.helper.auth.AuthInput
import com.loopcat.helper.auth.AuthPassword
import com.loopcat.helper.auth.AuthTitle
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.theme.White

const val NAVIGATION_SIGNUP = "signup"

@Preview
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var pwCheck by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(false) }

    var idError by remember { mutableStateOf(AuthErrorType.NONE) }
    var pwError by remember { mutableStateOf(AuthErrorType.NONE) }
    var pwCheckError by remember { mutableStateOf(AuthErrorType.NONE) }

    if (id.isNotEmpty() && pw.isNotEmpty() && idError == AuthErrorType.NONE && pwError == AuthErrorType.NONE && pwCheckError == AuthErrorType.NONE) {
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
            Spacer(modifier = modifier.height(44.dp))
            AuthInput(
                input = id,
                hint = stringResource(id = R.string.signup_id),
                onValueChange = { newId ->
                    id = newId
                    idError = if (checkIdRegex(id)) {
                        AuthErrorType.NONE
                    } else {
                        AuthErrorType.ID_REGEX
                    }
                }
            )
            AuthErrorMessage(errorType = idError)
            AuthPassword(
                input = pw,
                hint = stringResource(id = R.string.signup_pw),
                onValueChange = { newPw ->
                    pw = newPw
                    pwError = if (checkPwRegex(pw)) {
                        AuthErrorType.NONE
                    } else {
                        AuthErrorType.PW_REGEX
                    }
                }
            )
            AuthErrorMessage(errorType = pwError)
            AuthPassword(
                input = pwCheck,
                hint = stringResource(id = R.string.signup_pw_check),
                onValueChange = { newPwCheck ->
                    pwCheck = newPwCheck
                    if (pwCheck != pw) {
                        pwCheckError = AuthErrorType.NOT_SAME_PW
                    } else {
                        pwCheckError = AuthErrorType.NONE
                    }
                }
            )
            AuthErrorMessage(errorType = pwCheckError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnabled,
            buttonText = stringResource(id = R.string.signup_next),
            onClick = {
                // 아이디 중복 확인
                idError = AuthErrorType.OVERLAP_ID
            }
        )
    }
}

fun checkIdRegex(id: String): Boolean {
    val regex = "^[a-zA-Z0-9_]{5,20}".toRegex()
    return regex.matches(id)
}

fun checkPwRegex(pw: String): Boolean {
    val regex = "^[a-zA-Z0-9!#$%^&*+=_-]{8,20}".toRegex()
    return regex.matches(pw)
}
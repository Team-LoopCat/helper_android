package com.loopcat.helper.signup

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
import androidx.compose.ui.unit.dp
import com.loopcat.helper.R
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperInput
import com.loopcat.helper.ui.HelperPasswordInput
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.AuthTitle
import com.loopcat.helper.ui.auth.checkRegex
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

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
    } else {
        buttonEnabled = false
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
            HelperInput(
                input = id,
                hint = stringResource(id = R.string.signup_id),
                onValueChange = { input ->
                    id = input
                    idError = if (!checkRegex(AuthRegexType.ID, id)) {
                        AuthErrorType.ID_REGEX
                    } else {
                        AuthErrorType.NONE
                    }
                }
            )
            AuthErrorMessage(errorType = idError)
            HelperPasswordInput(
                input = pw, 
                hint = stringResource(id = R.string.signup_pw),
                onValueChange = { input ->
                    pw = input
                    pwError = if (!checkRegex(AuthRegexType.PASSWORD, pw)) {
                        AuthErrorType.PW_REGEX
                    } else {
                        AuthErrorType.NONE
                    }
                    if (pw != pwCheck) {
                        pwCheckError = AuthErrorType.NOT_SAME_PW
                    }
                }
            )
            AuthErrorMessage(errorType = pwError)
            HelperPasswordInput(
                input = pwCheck, 
                hint = stringResource(id = R.string.signup_pw_check),
                onValueChange = { input ->
                    pwCheck = input
                    if (pw != pwCheck) {
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
                // 다음 화면으로 이동
            }
        )
    }
}
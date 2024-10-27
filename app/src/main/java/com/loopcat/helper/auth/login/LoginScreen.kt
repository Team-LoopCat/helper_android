package com.loopcat.helper.auth.login

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
import com.loopcat.helper.addFocusCleaner
import com.loopcat.helper.auth.AuthErrorMessage
import com.loopcat.helper.auth.AuthErrorType
import com.loopcat.helper.auth.AuthInput
import com.loopcat.helper.auth.AuthPassword
import com.loopcat.helper.auth.AuthTitle
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.theme.White

const val NAVIGATION_LOGIN = "login"

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(AuthErrorType.NONE) }
    var buttonEnable by remember { mutableStateOf(false) }


    if (id.isNotEmpty() && pw.isNotEmpty()) {
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
            AuthTitle(title = stringResource(R.string.login))
            Spacer(modifier = modifier.height(46.dp))
            AuthInput(
                input = id,
                hint = stringResource(R.string.login_id),
                onValueChange = { newId ->
                    id = newId
                    loginError = AuthErrorType.NONE
                },
            )
            AuthPassword(
                input = pw,
                hint = stringResource(R.string.login_pw),
                onValueChange = { newPw ->
                    pw = newPw
                    loginError = AuthErrorType.NONE
                }
            )
            AuthErrorMessage(
                errorType = loginError
            )
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable,
            buttonText = stringResource(id = R.string.login),
            onClick = {
                // 로그인
                loginError = AuthErrorType.WRONG_ID_OR_PW
            }
        )
    }
}
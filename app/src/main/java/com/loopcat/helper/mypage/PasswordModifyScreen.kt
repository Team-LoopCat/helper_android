package com.loopcat.helper.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.loopcat.helper.ui.HelperAlertDialog
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperPasswordInput
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.checkRegex
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

@Composable
fun PasswordModifyScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    var nowPassword by remember { mutableStateOf("") }
    var changePassword by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }

    var nowPasswordError by remember { mutableStateOf(AuthErrorType.NONE) }
    var changePasswordError by remember { mutableStateOf(AuthErrorType.NONE) }
    val checkPasswordError = if (changePassword != checkPassword) {
        AuthErrorType.NOT_SAME_PW
    } else {
        AuthErrorType.NONE
    }

    val buttonEnable by remember() {
        derivedStateOf {
            nowPassword.isNotEmpty() &&
            changePassword.isNotEmpty() &&
            checkPassword.isNotEmpty() &&
            changePasswordError == AuthErrorType.NONE &&
            checkPasswordError == AuthErrorType.NONE
        }
    }

    var isFailModify by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        HelperTopBar(
            modifier = modifier.align(Alignment.TopCenter),
            title = stringResource(id = R.string.mypage_password_modify),
            isBack = true,
            onClick = {
                // 마이페이지로 이동
            }
        )
        PasswordInputs(
            modifier = modifier.align(Alignment.TopCenter),
            nowPassword = nowPassword,
            changePassword = changePassword,
            checkPassword = checkPassword,
            nowPasswordError = nowPasswordError,
            changePasswordError = changePasswordError,
            checkPasswordError = checkPasswordError,
            onNowPasswordChange = { input ->
                nowPassword = input
            },
            onChangePasswordChange = { input ->
                changePassword = input
                changePasswordError = if (!checkRegex(AuthRegexType.PASSWORD, changePassword)) {
                    AuthErrorType.PW_REGEX
                } else {
                    AuthErrorType.NONE
                }
            },
            onCheckPasswordChange = { input ->
                checkPassword = input
            }
        )
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable,
            buttonText = stringResource(id = R.string.complete_modify),
            onClick = {
                // 현재 비밀번호 일치 확인
                nowPasswordError = AuthErrorType.NOT_NOW_PW
                
                // 수정 성공 시 마이페이지

                // 실패 시
                isFailModify = true
            }
        )
    }
    if (isFailModify) {
        HelperAlertDialog(
            title = stringResource(id = R.string.password_modify_fail),
            onClick = {
                isFailModify = false
            }
        )
    }
}

@Composable
private fun PasswordInputs(
    modifier: Modifier = Modifier,
    nowPassword: String,
    changePassword: String,
    checkPassword: String,
    nowPasswordError: AuthErrorType,
    changePasswordError: AuthErrorType,
    checkPasswordError: AuthErrorType,
    onNowPasswordChange: (String) -> Unit,
    onChangePasswordChange: (String) -> Unit,
    onCheckPasswordChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(140.dp))
        HelperPasswordInput(
            input = nowPassword, 
            hint = stringResource(id = R.string.now_password),
            onValueChange = onNowPasswordChange
        )
        AuthErrorMessage(errorType = nowPasswordError)
        HelperPasswordInput(
            input = changePassword,
            hint = stringResource(id = R.string.change_password),
            onValueChange = onChangePasswordChange
        )
        AuthErrorMessage(errorType = changePasswordError)
        HelperPasswordInput(
            input = checkPassword,
            hint = stringResource(id = R.string.signup_pw_check),
            onValueChange = onCheckPasswordChange
        )
        AuthErrorMessage(errorType = checkPasswordError)
    }
}
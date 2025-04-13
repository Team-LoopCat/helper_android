package com.loopcat.helper.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loopcat.helper.R
import com.loopcat.helper.login.viewmodel.LoginViewModel
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperInput
import com.loopcat.helper.ui.HelperPasswordInput
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthTitle
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

const val NAVIGATION_LOGIN = "login"

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navToSignUp: () -> Unit,
    navToMain: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val id = viewModel.id
    val pw = viewModel.pw

    val isLoading = viewModel.isLoading
    val isLoginSuccess = viewModel.isLoginSuccess

    val buttonEnable by remember(id, pw) {
        derivedStateOf {
            !isLoading &&
            id.isNotEmpty() &&
            pw.isNotEmpty()
        }
    }

    if (isLoginSuccess == true) {
        LaunchedEffect(Unit) {
            navToMain()
        }
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
            Spacer(modifier = modifier.height(100.dp))
            AuthTitle(text = stringResource(id = R.string.login))
            Spacer(modifier = modifier.height(60.dp))
            HelperInput(
                input = id,
                hint = stringResource(id = R.string.login_id),
                onValueChange = { input ->
                    viewModel.onIdChange(input)
                }
            )
            AuthErrorMessage(errorType = AuthErrorType.NONE)
            HelperPasswordInput(
                input = pw,
                hint = stringResource(id = R.string.login_pw),
                onValueChange = { input ->
                    viewModel.onPwChange(input)
                }
            )
            AuthErrorMessage(errorType = if (isLoginSuccess == false) AuthErrorType.WRONG_ID_OR_PW else AuthErrorType.NONE)
        }
        Column(
            modifier = modifier
                .padding(
                    bottom = 40.dp
                )
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HelperButton(
                modifier = modifier
                    .offset(
                        y = 30.dp
                    ),
                enable = buttonEnable,
                buttonText = stringResource(id = R.string.login),
                onClick = {
                    viewModel.onLoginClick()
                }
            )
            NotMember(
                onClick = navToSignUp
            )
        }
    }

}

@Composable
private fun NotMember(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                top = 14.dp
            )
    ) {
        Text(
            text = stringResource(id = R.string.login_not_member),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Black
            )
        )
        Text(
            modifier = modifier
                .padding(
                    start = 6.dp
                )
                .clickable {
                    onClick()
                },
            text = stringResource(id = R.string.signup),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Main
            )
        )
    }
}
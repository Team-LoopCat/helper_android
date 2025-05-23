package com.loopcat.helper.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loopcat.helper.R
import com.loopcat.helper.signup.viewmodel.SignupViewModel
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperInput
import com.loopcat.helper.ui.HelperPasswordInput
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthTitle
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

const val NAVIGATION_SIGNUP = "signUp"

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    BackHandler {
        onBack()
    }

    val id = viewModel.id
    val pw = viewModel.pw
    val pwCheck = viewModel.pwCheck

    val idError = viewModel.idError
    val pwError = viewModel.pwError
    val pwCheckError = viewModel.pwCheckError

    val isLoading = viewModel.isLoading
    val isSuccess = viewModel.isIdValidation

    val buttonEnable by remember(id, pw, idError, pwError, pwCheckError) {
        derivedStateOf {
            !isLoading &&
            id.isNotEmpty() &&
            pw.isNotEmpty() &&
            idError == AuthErrorType.NONE &&
            pwError == AuthErrorType.NONE &&
            pwCheckError == AuthErrorType.NONE
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
            AuthTitle(text = stringResource(id = R.string.signup))
            Spacer(modifier = modifier.height(60.dp))
            HelperInput(
                input = id,
                hint = stringResource(id = R.string.signup_id),
                onValueChange = { input ->
                    viewModel.onIdChange(input)
                }
            )
            AuthErrorMessage(errorType = idError)
            HelperPasswordInput(
                input = pw, 
                hint = stringResource(id = R.string.signup_pw),
                onValueChange = { input ->
                    viewModel.onPwChange(input)
                }
            )
            AuthErrorMessage(errorType = pwError)
            HelperPasswordInput(
                input = pwCheck, 
                hint = stringResource(id = R.string.signup_pw_check),
                onValueChange = { input ->
                    viewModel.onPwCheckChange(input)
                }
            )
            AuthErrorMessage(errorType = pwCheckError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable,
            buttonText = stringResource(id = R.string.signup_next),
            onClick = {
                viewModel.onSignupNextClick()
            }
        )
    }

    if (isSuccess == true) {
        onNext()
    }
}
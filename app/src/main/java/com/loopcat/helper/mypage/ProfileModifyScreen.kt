package com.loopcat.helper.mypage

import android.net.Uri
import android.util.Log
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
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.HelperConfirmDialog
import com.loopcat.helper.ui.HelperInput
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.LargeDropdown
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.ProfileModify
import com.loopcat.helper.ui.auth.checkRegex
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

@Composable
fun ProfileModifyScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val options = listOf("선택과목 없음", "선택과목1", "선택과목2")

    var profileUrl by remember { mutableStateOf("") }
    
    var nickname by remember { mutableStateOf("") }
    var nicknameError by remember { mutableStateOf(AuthErrorType.NONE) }

    var selectedSubject by remember { mutableStateOf(options[0]) }
    
    val buttonEnable by remember(nickname) {
        derivedStateOf {
            nickname.isNotEmpty() &&
            nicknameError == AuthErrorType.NONE
        }
    }
    
    var isComplete by remember { mutableStateOf(false) }
    
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        HelperTopBar(
            modifier = modifier.align(Alignment.TopCenter),
            title = stringResource(id = R.string.mypage_profile_modify),
            isBack = true,
            onClick = {
                // 이전 페이지로 이동
            }
        )
        MyPageProfileModify(
            modifier = modifier.align(Alignment.TopCenter),
            profileUrl = profileUrl,
            nickname = nickname,
            selectedSubject = selectedSubject,
            nicknameError = nicknameError,
            options = options,
            onImageSelected = { uri ->
                profileUrl = uri.toString()
            },
            onNicknameChange = { input ->
                nickname = input
                nicknameError = if (!checkRegex(AuthRegexType.NICKNAME, nickname)) {
                    AuthErrorType.NICK_REGEX
                } else {
                    AuthErrorType.NONE
                }
            },
            onSubjectSelect = { option ->
                selectedSubject = option
            }
        )
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable,
            buttonText = stringResource(id = R.string.complete_modify),
            onClick = {
                isComplete = true
                // 프로필 수정
            }
        )
    }
    if (isComplete) {
        HelperConfirmDialog(
            title = stringResource(id = R.string.is_profile_modify),
            onClickCancel = {
                isComplete = false
            },
            onClickConfirm = {
                // 닉네임 중복 검사
                // 성공 시 마이페이지로 이동
                // 실패시 AlertDialog
            }
        )
    }
}

@Composable
private fun MyPageProfileModify(
    modifier: Modifier = Modifier,
    profileUrl: String,
    nickname: String,
    selectedSubject: String,
    nicknameError: AuthErrorType,
    options: List<String>,
    onImageSelected: (Uri) -> Unit,
    onNicknameChange: (String) -> Unit,
    onSubjectSelect: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(140.dp))
        ProfileModify(
            imageUrl = profileUrl,
            onImageSelected = onImageSelected
        )
        Spacer(modifier = modifier.height(36.dp))
        HelperInput(
            input = nickname,
            hint = stringResource(id = R.string.signup_nick),
            onValueChange = onNicknameChange
        )
        AuthErrorMessage(errorType = nicknameError)
        LargeDropdown(
            isCategory = false,
            options = options,
            selectedOption = selectedSubject,
            onOptionSelected = onSubjectSelect
        )
    }
}
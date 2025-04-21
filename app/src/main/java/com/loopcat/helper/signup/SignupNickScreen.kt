package com.loopcat.helper.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.loopcat.helper.R
import com.loopcat.helper.signup.viewmodel.SignupViewModel
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.InputPlaceHolder
import com.loopcat.helper.ui.auth.AuthErrorMessage
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.ProfileModify
import com.loopcat.helper.ui.auth.SmallInputWithButton
import com.loopcat.helper.ui.auth.checkRegex
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.addFocusCleaner

const val NAVIGATION_SIGNUP_NICK = "signUpNick"

@Composable
fun SignupNickScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
    onBack: () -> Unit,
    navToLogin: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    BackHandler {
        onBack()
    }

    val profileImageUrl = viewModel.profileImage
    val grade = viewModel.grade
    val classNumber = viewModel.classroom
    val studentNumber = viewModel.studentNumber
    val nickname = viewModel.nickname
    val sendNick = viewModel.sendNick

    val nickError = viewModel.nickError
    val schoolInfoError = if (grade.isNotEmpty() && grade.toIntOrNull() !in 1..3) {
        AuthErrorType.GRADE_REGEX
    } else if (classNumber.isNotEmpty() && classNumber.toIntOrNull() !in 1..4) {
        AuthErrorType.CLASS_NUMBER_REGEX
    } else if (studentNumber.isNotEmpty() && studentNumber.toIntOrNull() !in 1..20) {
        AuthErrorType.STUDENT_NUMBER_REGEX
    } else {
        AuthErrorType.NONE
    }

    val isLoading = viewModel.isLoading
    val isCheckDuplicateNick = viewModel.isCheckDuplicateNick
    val isSignUpSuccess = viewModel.isSignUpSuccess

    val smallButtonEnable by remember(isLoading, nickname, nickError) {
        derivedStateOf {
            !isLoading &&
            nickname.isNotEmpty() &&
            nickError != AuthErrorType.NICK_REGEX
        }
    }
    val buttonEnable by remember(isLoading, isCheckDuplicateNick, schoolInfoError, sendNick, nickError) {
        derivedStateOf {
            !isLoading &&
            isCheckDuplicateNick &&
            grade.isNotEmpty() &&
            classNumber.isNotEmpty() &&
            studentNumber.isNotEmpty() &&
            nickname.isNotEmpty() &&
            schoolInfoError == AuthErrorType.NONE &&
            sendNick == nickname &&
            nickError == AuthErrorType.NONE
        }
    }

    if (isCheckDuplicateNick) {
        Toast.makeText(context, "사용 가능한 낙네임입니다", Toast.LENGTH_SHORT).show()
    }
    if (isSignUpSuccess == true) {
        navToLogin()
    } else if (isSignUpSuccess == false) {
        Toast.makeText(context, "회원가입 실패", Toast.LENGTH_SHORT).show()
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
            ProfileModify(
                imageUrl = profileImageUrl,
                onImageSelected = { uri ->
                    viewModel.onProfileImageChange(uri)
                }
            )
            Spacer(modifier = modifier.height(60.dp))
            SchoolInfo(
                grade = grade,
                classNumber = classNumber,
                studentNumber = studentNumber,
                onGradeChange = { input ->
                    if (input.isDigitsOnly() && input.length <= 1) {
                        viewModel.onGradeChange(input)
                    }
                },
                onClassNumberChange = { input ->
                    if (input.isDigitsOnly() && input.length <= 1) {
                        viewModel.onClassroomChange(input)
                    }
                },
                onStudentNumberChange = { input ->
                    if (input.isDigitsOnly() && input.length <= 2) {
                        viewModel.onStudentNumberChange(input)
                    }
                }
            )
            AuthErrorMessage(errorType = schoolInfoError)
            SmallInputWithButton(
                input = nickname,
                hint = stringResource(id = R.string.signup_nick),
                enable = smallButtonEnable,
                buttonText = stringResource(id = R.string.signup_overlap_check),
                onValueChange = { input ->
                    viewModel.onNickChange(input)
                },
                onClick = {
                    viewModel.onCheckNickDuplicate()
                }
            )
            AuthErrorMessage(errorType = nickError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable, 
            buttonText = stringResource(id = R.string.signup),
            onClick = {
                viewModel.signUp()
            }
        )
    }
}

@Composable
fun SchoolInfoInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
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
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Black
            ),
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField -> 
                if (input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
    }
}

@Composable
fun SchoolInfo(
    modifier: Modifier = Modifier,
    grade: String,
    classNumber: String,
    studentNumber: String,
    onGradeChange: (String) -> Unit,
    onClassNumberChange: (String) -> Unit,
    onStudentNumberChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
    ) {
        SchoolInfoInput(
            modifier = modifier.weight(1f),
            input = grade, 
            hint = stringResource(id = R.string.signup_grade),
            onValueChange = onGradeChange
        )
        Spacer(modifier = modifier.width(8.dp))
        SchoolInfoInput(
            modifier = modifier.weight(1f),
            input = classNumber,
            hint = stringResource(id = R.string.signup_class_number),
            onValueChange = onClassNumberChange
        )
        Spacer(modifier = modifier.width(8.dp))
        SchoolInfoInput(
            modifier = modifier.weight(1f),
            input = studentNumber,
            hint = stringResource(id = R.string.signup_student_number),
            onValueChange = onStudentNumberChange
        )
    }
}
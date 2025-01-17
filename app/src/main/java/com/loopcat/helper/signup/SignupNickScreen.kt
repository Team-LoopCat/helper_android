package com.loopcat.helper.signup

import android.net.Uri
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
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

@Composable
fun SignupNickScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    var imageUri by remember {
        mutableStateOf(Uri.parse("android.resource://com.loopcat.helper/drawable/default_profile"))
    }
    var grade by remember { mutableStateOf("") }
    var classNumber by remember { mutableStateOf("") }
    var studentNumber by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    
    var nickError by remember { mutableStateOf(AuthErrorType.NONE) }

    val smallButtonEnable by remember(nickname, nickError) {
        derivedStateOf {
            nickname.isNotEmpty() &&
            nickError != AuthErrorType.NICK_REGEX
        }
    }
    val buttonEnable by remember(grade, classNumber, studentNumber, nickname, nickError) {
        derivedStateOf {
            grade.isNotEmpty() &&
            classNumber.isNotEmpty() &&
            studentNumber.isNotEmpty() &&
            nickname.isNotEmpty() &&
            nickError == AuthErrorType.NONE
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
            ProfileModify(
                imageUrl = imageUri,
                onImageSelected = { uri ->
                    imageUri = uri
                }
            )
            Spacer(modifier = modifier.height(60.dp))
            SchoolInfo(
                grade = grade,
                classNumber = classNumber,
                studentNumber = studentNumber,
                onGradeChange = { input ->
                    if (grade.isEmpty()) {
                        grade = input
                    }
                },
                onClassNumberChange = { input ->
                    if (classNumber.isEmpty()) {
                        classNumber = input
                    }
                },
                onStudentNumberChange = { input ->
                    if (studentNumber.length < 2) {
                        studentNumber = input
                    }
                }
            )
            AuthErrorMessage(errorType = AuthErrorType.NONE)
            SmallInputWithButton(
                input = nickname,
                hint = stringResource(id = R.string.signup_nick),
                enable = smallButtonEnable,
                buttonText = stringResource(id = R.string.signup_overlap_check),
                onValueChange = { input ->
                    nickname = input
                    nickError = if (!checkRegex(AuthRegexType.NICKNAME, nickname)) {
                        AuthErrorType.NICK_REGEX
                    } else {
                        AuthErrorType.NONE
                    }
                },
                onClick = {
                    // 중복확인 요청
                }
            )
            AuthErrorMessage(errorType = nickError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnable, 
            buttonText = stringResource(id = R.string.signup),
            onClick = {
                // 회원가입
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
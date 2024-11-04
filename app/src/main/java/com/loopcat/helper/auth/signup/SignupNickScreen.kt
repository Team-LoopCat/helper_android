package com.loopcat.helper.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.addFocusCleaner
import com.loopcat.helper.auth.AuthErrorMessage
import com.loopcat.helper.auth.AuthErrorType
import com.loopcat.helper.auth.AuthInput
import com.loopcat.helper.auth.AuthInputPlaceholder
import com.loopcat.helper.ui.HelperButton
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

const val NAVIGATION_SIGNUP_NICK = "signupNick"

@Composable
fun SignupNickScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    var grade by remember { mutableStateOf("") }
    var classNumber by remember { mutableStateOf("") }
    var studentNumber by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var buttonEnabled by remember { mutableStateOf(false) }

    var nickError by remember { mutableStateOf(AuthErrorType.NONE) }

    if (grade.isNotEmpty() && classNumber.isNotEmpty() && studentNumber.isNotEmpty() && nickname.isNotEmpty() && nickError == AuthErrorType.NONE) {
        buttonEnabled = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .addFocusCleaner(focusManager)
    ) {
        Column(
            modifier = modifier.align(Alignment.TopCenter)
        ) {
            SchoolInfo(
                grade = grade,
                classNumber = classNumber,
                studentNumber = studentNumber,
                onGradeChange = { newGrade ->
                    grade = newGrade
                },
                onClassChange = { newClass ->
                    classNumber = newClass
                },
                onNumberChange = { newNumber ->
                    studentNumber = newNumber
                }
            )
            NickInput(
                nickname = nickname,
                onValueChange = { newNick ->
                    nickname = newNick
                    nickError = if (!checkNickRegex(nickname)) {
                        AuthErrorType.NICK_REGEX
                    } else {
                        AuthErrorType.NONE
                    }
                },
                onClick = {
                    // 아이디 중복 확인
                }
            )
            AuthErrorMessage(errorType = nickError)
        }
        HelperButton(
            modifier = modifier.align(Alignment.BottomCenter),
            enable = buttonEnabled,
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
    OutlinedTextField(
        modifier = modifier
            .width(110.dp),
        value = input,
        onValueChange = onValueChange,
        placeholder = {
            AuthInputPlaceholder(hint = hint)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
        )
    )
}

@Composable
fun SchoolInfo(
    modifier: Modifier = Modifier,
    grade: String,
    classNumber: String,
    studentNumber: String,
    onGradeChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onNumberChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SchoolInfoInput(
            input = grade,
            hint = stringResource(id = R.string.signup_grade),
            onValueChange = onGradeChange
        )
        SchoolInfoInput(
            input = classNumber,
            hint = stringResource(id = R.string.signup_class),
            onValueChange = onClassChange
        )
        SchoolInfoInput(
            input = studentNumber,
            hint = stringResource(id = R.string.signup_number),
            onValueChange = onNumberChange
        )
    }
}

@Composable
fun NickInput(
    modifier: Modifier = Modifier,
    nickname: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 0.dp,
                end = 24.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthInput(
            modifier = modifier
                .padding(
                    end = (-10).dp
                )
                .fillMaxWidth(fraction = 0.75f),
            input = nickname,
            hint = stringResource(id = R.string.signup_nick),
            onValueChange = onValueChange
        )
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.8f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Gray300,
                disabledContentColor = White,
                containerColor = Main,
                contentColor = White
            )
        ) {
            Text(
                text = stringResource(id = R.string.signup_check_overlap),
                style = TextStyle(
                    color = Black,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            )
        }
    }
}

fun checkNickRegex(nick: String): Boolean {
    val regex = "^[a-zA-Z0-9가-힣!#$%^&*/'\";:_=+-]{2,10}".toRegex()
    return regex.matches(nick)
}
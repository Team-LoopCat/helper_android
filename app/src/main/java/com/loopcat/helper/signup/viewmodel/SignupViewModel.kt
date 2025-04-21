package com.loopcat.helper.signup.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loopcat.helper.signup.repository.SignupRepository
import com.loopcat.helper.ui.auth.AuthErrorType
import com.loopcat.helper.ui.auth.AuthRegexType
import com.loopcat.helper.ui.auth.checkRegex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: SignupRepository
) : ViewModel() {
    var id by mutableStateOf("")
    var pw by mutableStateOf("")
    var pwCheck by mutableStateOf("")

    var email by mutableStateOf("")
    private var sendMail by mutableStateOf("")
    var code by mutableStateOf("")
    var codeTime by mutableIntStateOf(300)

    var grade by mutableStateOf("")
    var classroom by mutableStateOf("")
    var studentNumber by mutableStateOf("")
    var nickname by mutableStateOf("")
    var profileImage by mutableStateOf("")
    var sendNick by mutableStateOf("")

    var idError by mutableStateOf(AuthErrorType.NONE)
    var pwError by mutableStateOf(AuthErrorType.NONE)
    var pwCheckError by mutableStateOf(AuthErrorType.NONE)

    var mailError by mutableStateOf(AuthErrorType.NONE)
    var codeError by mutableStateOf(AuthErrorType.NONE)

    var nickError by mutableStateOf(AuthErrorType.NONE)

    var isLoading by mutableStateOf(false)
    var isIdValidation by mutableStateOf(false)
    private var isSendMail by mutableStateOf(false)
    var isVerifyCode by mutableStateOf(false)
    var isCheckDuplicateNick by mutableStateOf(false)
    var isSignUpSuccess by mutableStateOf<Boolean?>(false)

    fun onIdChange(input: String) {
        id = input
        idError = if (checkRegex(AuthRegexType.ID, id)) AuthErrorType.NONE else AuthErrorType.ID_REGEX
    }
    fun onPwChange(input: String) {
        pw = input
        pwError = if(checkRegex(AuthRegexType.PASSWORD, pw)) AuthErrorType.NONE else AuthErrorType.PW_REGEX
        pwCheckError = if (pw == pwCheck) AuthErrorType.NONE else AuthErrorType.NOT_SAME_PW
    }
    fun onPwCheckChange(input: String) {
        pwCheck = input
        pwCheckError = if (pw == pwCheck) AuthErrorType.NONE else AuthErrorType.NOT_SAME_PW
    }
    fun onSignupNextClick() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.checkDuplicateId(id)
            isLoading = false
            result.onSuccess {
                isIdValidation = true
            }.onFailure {
                if (it.message == "Overlap Id") {
                    idError = AuthErrorType.OVERLAP_ID
                }
                isIdValidation = false
            }
        }
    }

    fun onMailChange(input: String) {
        email = input
        mailError = if (checkRegex(AuthRegexType.MAIL, email)) AuthErrorType.NONE else AuthErrorType.MAIL_REGEX
    }
    fun onSendMailClick() {
        sendMail = email
        viewModelScope.launch {
            isLoading = true
            val result = repository.sendMail(email)
            isLoading = false
            result.onSuccess {
                isSendMail = true
            }.onFailure {
                if (it.message == "Overlap Mail") {
                    mailError = AuthErrorType.OVERLAP_MAIL
                }
                isSendMail = false
            }
        }
    }
    fun onStartCodeTimer() {
        if (isSendMail) {
            viewModelScope.launch {
                while (codeTime > 0) {
                    delay(1000L)
                    codeTime--
                }
            }
        }
    }
    fun onCodeChange(input: String) {
        code = input
    }
    fun onMailNextClick() {
        if (sendMail != email) {
            mailError = AuthErrorType.MAIL_MODIFY
            return
        }
        if (codeTime <= 0) {
            mailError = AuthErrorType.CODE_TIMEOUT
            return
        }
        viewModelScope.launch {
            val result = repository.verifyMail(email, code)
            result.onSuccess {
                isVerifyCode = true
            }.onFailure {
                if (it.message == "TimeOut") {
                    codeError = AuthErrorType.CODE_TIMEOUT
                } else if (it.message == "Wrong Code") {
                    codeError = AuthErrorType.WRONG_CODE
                }
            }
        }
    }

    fun onGradeChange(input: String) {
        grade = input
    }
    fun onClassroomChange(input: String) {
        classroom = input
    }
    fun onStudentNumberChange(input: String) {
        studentNumber = input
    }
    fun onNickChange(input: String) {
        nickname = input
        nickError = if(checkRegex(AuthRegexType.NICKNAME, nickname)) AuthErrorType.NONE else AuthErrorType.NICK_REGEX
        if (isCheckDuplicateNick) {
            isCheckDuplicateNick = false
        }
    }
    fun onProfileImageChange(uri: Uri) {
        profileImage = uri.toString()
    }
    fun onCheckNickDuplicate() {
        sendNick = nickname
        viewModelScope.launch {
            isLoading = true
            val result = repository.checkDuplicateNick(sendNick)
            isLoading = false
            result.onSuccess {
                isCheckDuplicateNick = true
            }.onFailure {
                if (it.message == "Overlap Nickname") {
                    nickError = AuthErrorType.OVERLAP_NICK
                }
            }
        }
    }
    fun signUp() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.signUp(id, pw, email, grade, classroom, studentNumber, nickname, profileImage)
            isLoading = false
            result.onSuccess {
                isSignUpSuccess = true
            }.onFailure {
                isSignUpSuccess = false
            }
        }
    }
}
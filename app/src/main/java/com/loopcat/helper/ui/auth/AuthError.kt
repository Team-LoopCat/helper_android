package com.loopcat.helper.ui.auth

import com.loopcat.helper.R

enum class AuthErrorType {
    WRONG_ID_OR_PW,
    NONE
}

fun authErrorMessage(error: AuthErrorType): Int {
    return when(error) {
        AuthErrorType.WRONG_ID_OR_PW -> R.string.error_login
        AuthErrorType.NONE -> R.string.error_none
    }
}
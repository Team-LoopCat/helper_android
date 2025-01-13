package com.loopcat.helper.ui.auth

import com.loopcat.helper.R

enum class AuthErrorType {
    ID_REGEX,
    PW_REGEX,
    NOT_SAME_PW,
    OVERLAP_ID,

    WRONG_ID_OR_PW,
    NONE
}

fun authErrorMessage(error: AuthErrorType): Int {
    return when (error) {
        AuthErrorType.ID_REGEX -> R.string.error_id_regex
        AuthErrorType.PW_REGEX -> R.string.error_pw_regex
        AuthErrorType.NOT_SAME_PW -> R.string.error_not_same_pw
        AuthErrorType.OVERLAP_ID -> R.string.error_overlap_id

        AuthErrorType.WRONG_ID_OR_PW -> R.string.error_login
        AuthErrorType.NONE -> R.string.error_none
    }
}
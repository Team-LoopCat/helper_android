package com.loopcat.helper.auth

import com.loopcat.helper.R

enum class AuthErrorType {
    ID_REGEX,
    PW_REGEX,
    NICK_REGEX,

    OVERLAP_ID,
    OVERLAP_MAIL,
    OVERLAP_NICK,

    NOT_SAME_PW,
    WRONG_CODE,
    CODE_TIMEOUT,

    WRONG_ID_OR_PW,
    NONE
}

fun authErrorMessage(error: AuthErrorType): Int {
    return when(error) {
        AuthErrorType.ID_REGEX -> R.string.error_id_regex
        AuthErrorType.PW_REGEX -> R.string.error_pw_regex
        AuthErrorType.NOT_SAME_PW -> R.string.error_not_same_pw
        AuthErrorType.OVERLAP_ID -> R.string.error_overlap_id

        AuthErrorType.OVERLAP_MAIL -> R.string.error_overlap_mail
        AuthErrorType.WRONG_CODE -> R.string.error_wrong_code
        AuthErrorType.CODE_TIMEOUT -> R.string.error_code_timeout

        AuthErrorType.NICK_REGEX -> R.string.error_nick_regex
        AuthErrorType.OVERLAP_NICK -> R.string.error_overlap_nick

        AuthErrorType.WRONG_ID_OR_PW -> R.string.error_login
        AuthErrorType.NONE -> R.string.error_none
    }
}
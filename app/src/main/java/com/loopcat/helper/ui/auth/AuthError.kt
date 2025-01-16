package com.loopcat.helper.ui.auth

import com.loopcat.helper.R

enum class AuthErrorType {
    ID_REGEX,
    PW_REGEX,
    NOT_SAME_PW,
    OVERLAP_ID,

    MAIL_REGEX,
    OVERLAP_MAIL,
    MAIL_MODIFY,
    WRONG_CODE,
    CODE_TIMEOUT,

    NICK_REGEX,
    OVERLAP_NICK,

    WRONG_ID_OR_PW,
    NONE
}

fun authErrorMessage(error: AuthErrorType): Int {
    return when (error) {
        AuthErrorType.ID_REGEX -> R.string.error_id_regex
        AuthErrorType.PW_REGEX -> R.string.error_pw_regex
        AuthErrorType.NOT_SAME_PW -> R.string.error_not_same_pw
        AuthErrorType.OVERLAP_ID -> R.string.error_overlap_id

        AuthErrorType.MAIL_REGEX -> R.string.error_mail_regex
        AuthErrorType.OVERLAP_MAIL -> R.string.error_overlap_mail
        AuthErrorType.MAIL_MODIFY -> R.string.error_mail_modify
        AuthErrorType.WRONG_CODE -> R.string.error_wrong_code
        AuthErrorType.CODE_TIMEOUT -> R.string.error_timeout

        AuthErrorType.NICK_REGEX -> R.string.error_nick_regex
        AuthErrorType.OVERLAP_NICK -> R.string.error_overlap_nick

        AuthErrorType.WRONG_ID_OR_PW -> R.string.error_login
        AuthErrorType.NONE -> R.string.error_none
    }
}
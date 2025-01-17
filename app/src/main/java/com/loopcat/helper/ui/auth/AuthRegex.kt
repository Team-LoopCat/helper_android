package com.loopcat.helper.ui.auth

enum class AuthRegexType {
    ID,
    PASSWORD,
    MAIL,
    NICKNAME
}

fun checkRegex(type: AuthRegexType, input: String): Boolean {
    val regex = when(type) {
        AuthRegexType.ID -> "^[a-zA-Z0-9_\\.-]{5,20}$"
        AuthRegexType.PASSWORD -> "^[a-zA-Z0-9~!@#$%^&*()_+=?\\.-]{8,20}$"
        AuthRegexType.MAIL -> "^[a-zA-Z0-9._%+-]+@dsm\\.hs\\.kr$"
        AuthRegexType.NICKNAME -> "^[a-zA-Z0-9ㄱ-ㅎ가-힣_\\.-]{2,10}$"
    }.toRegex()
    return regex.matches(input)
}
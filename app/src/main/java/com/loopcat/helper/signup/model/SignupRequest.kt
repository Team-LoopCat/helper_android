package com.loopcat.helper.signup.model

data class SignupRequest(
    val id: String,
    val password: String,
    val email: String,
    val grade: String,
    val classroom: String,
    val number: String,
    val nickname: String,
    val profile: String
)
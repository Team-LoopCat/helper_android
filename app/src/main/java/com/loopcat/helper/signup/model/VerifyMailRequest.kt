package com.loopcat.helper.signup.model

data class VerifyMailRequest(
    val email: String,
    val code: String
)

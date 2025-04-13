package com.loopcat.helper.login.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
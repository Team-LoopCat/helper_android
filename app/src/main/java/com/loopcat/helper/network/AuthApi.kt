package com.loopcat.helper.network

import com.loopcat.helper.login.model.LoginRequest
import com.loopcat.helper.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
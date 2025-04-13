package com.loopcat.helper.login.repository

import com.loopcat.helper.login.model.LoginRequest
import com.loopcat.helper.login.model.LoginResponse
import com.loopcat.helper.network.AuthApi

class LoginRepository(private val authApi: AuthApi) {
    suspend fun login(id: String, pw: String): Result<LoginResponse> {
        return try {
            val response = authApi.login(LoginRequest(id, pw))
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("로그인 실패"))
            } else {
                Result.failure(Exception("로그인 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package com.loopcat.helper.network

import com.loopcat.helper.signup.model.SendMailRequest
import com.loopcat.helper.signup.model.SignupRequest
import com.loopcat.helper.signup.model.VerifyMailRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentApi {
    // TODO: 수정
    @POST("/student/{id}")
    suspend fun checkDuplicateId(@Path("id") id: String): Response<Unit>

    @POST("/student/email/send")
    suspend fun sendMail(@Body request: SendMailRequest): Response<Unit>

    @POST("/student/email/verify")
    suspend fun verifyMail(@Body request: VerifyMailRequest): Response<Unit>

    // TODO: 수정
    @POST("/student/{nickname}")
    suspend fun checkDuplicateNick(@Path("nickname") nickname: String): Response<Unit>

    @POST("/student/signup")
    suspend fun signUp(@Body request: SignupRequest): Response<Unit>
}
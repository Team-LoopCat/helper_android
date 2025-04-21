package com.loopcat.helper.signup.repository

import com.loopcat.helper.network.StudentApi
import com.loopcat.helper.signup.model.SendMailRequest
import com.loopcat.helper.signup.model.SignupRequest
import com.loopcat.helper.signup.model.VerifyMailRequest

class SignupRepository(private val api: StudentApi) {
    suspend fun checkDuplicateId(id: String): Result<Unit> {
        return try {
            // TODO: API 명세 나오면 수정
            val response = api.checkDuplicateId(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else if (response.code() == 409) {
                Result.failure(Exception("Overlap Id"))
            } else {
                Result.failure(Exception("아이디 중복 확인 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendMail(email: String): Result<Unit> {
        return try {
            val response = api.sendMail(SendMailRequest(email))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else if (response.code() == 409) {
                Result.failure(Exception("Overlap Email"))
            } else {
                Result.failure(Exception("이메일 전송 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun verifyMail(email: String, code: String): Result<Unit> {
        return try {
            val response = api.verifyMail(VerifyMailRequest(email, code))
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("코드 인증 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkDuplicateNick(nickname: String): Result<Unit> {
        return try {
            // TODO: 수정
            val response = api.checkDuplicateNick(nickname)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else if (response.code() == 409) {
                Result.failure(Exception("Overlap Nickname"))
            } else {
                Result.failure(Exception("닉네임 중복 확인 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(
        id: String,
        password: String,
        email: String,
        grade: String,
        classroom: String,
        number: String,
        nickname: String,
        profile: String
    ): Result<Unit> {
        return try {
            val response = api.signUp(
                SignupRequest(
                    id, password, email,
                    grade, classroom, number,
                    nickname, profile
                )
            )
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("회원가입 실패 ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
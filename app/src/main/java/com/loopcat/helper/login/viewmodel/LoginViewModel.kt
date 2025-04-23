package com.loopcat.helper.login.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loopcat.helper.local.AuthPreference
import com.loopcat.helper.login.repository.LoginRepository
import com.loopcat.helper.utils.accessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private val application = Application()
    private val authPreference = AuthPreference(application)

    var id by mutableStateOf("")
    var pw by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isLoginSuccess by mutableStateOf<Boolean?>(null)

    fun onIdChange(input: String) {
        id = input
    }

    fun onPwChange(input: String) {
        pw = input
    }

    fun onLoginClick() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.login(id, pw)
            isLoading = false
            result.onSuccess {
                result.getOrNull()?.accessToken?.let { token ->
                    authPreference.setAccessToken(token)
                    accessToken = token
                }
                isLoginSuccess = true

            }.onFailure {
                isLoginSuccess = false
            }
        }
    }
}
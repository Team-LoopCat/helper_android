package com.loopcat.helper.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loopcat.helper.login.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
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
                isLoginSuccess = true
            }.onFailure {
                isLoginSuccess = false
            }
        }
    }
}
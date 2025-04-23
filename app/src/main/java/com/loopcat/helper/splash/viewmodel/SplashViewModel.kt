package com.loopcat.helper.splash.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.loopcat.helper.local.AuthPreference
import com.loopcat.helper.utils.accessToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val authPreference = AuthPreference(application)

    private val _isLoggedIn = mutableStateOf<Boolean?>(null)
    val isLoggedIn: State<Boolean?> = _isLoggedIn

    init {
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        viewModelScope.launch {
            delay(2000)
            val token = authPreference.getAccessToken()
            accessToken = token ?: ""
            _isLoggedIn.value = token != null
        }
    }
}
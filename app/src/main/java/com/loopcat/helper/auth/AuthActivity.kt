package com.loopcat.helper.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.loopcat.helper.MainActivity
import com.loopcat.helper.auth.splash.HelperSplashScreen
import com.loopcat.helper.auth.splash.viewmodel.SplashViewModel
import com.loopcat.helper.navigation.AuthNavigation
import com.loopcat.helper.ui.theme.HelperTheme

@SuppressLint("CustomSplashScreen")
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelperSplashScreen(
                navToLogin = {
                    setContent {
                        AuthNavigation(
                            navToMain = {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                        )
                    }
                },
                navToMain = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            )
        }
    }
}
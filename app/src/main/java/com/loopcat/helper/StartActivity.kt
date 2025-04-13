package com.loopcat.helper

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loopcat.helper.splash.HelperSplashScreen
import com.loopcat.helper.navigation.AuthNavigation

@SuppressLint("CustomSplashScreen")
class StartActivity : ComponentActivity() {
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
package com.loopcat.helper.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.loopcat.helper.MainActivity
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.HelperTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelperTheme {
                HelperSplashScreen(
                    navToMain = {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                        finish()
                    }
                )
            }
        }
    }
}
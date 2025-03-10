package com.loopcat.helper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loopcat.helper.navigation.AuthNavigation
import com.loopcat.helper.ui.theme.HelperTheme

@SuppressLint("CustomSplashScreen")
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelperTheme {
                AuthNavigation(
                    navController = rememberNavController()
                )
            }
        }
    }
}
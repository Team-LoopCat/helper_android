package com.loopcat.helper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loopcat.helper.login.LoginScreen
import com.loopcat.helper.login.NAVIGATION_LOGIN
import com.loopcat.helper.signup.NAVIGATION_SIGNUP
import com.loopcat.helper.signup.NAVIGATION_SIGNUP_MAIL
import com.loopcat.helper.signup.NAVIGATION_SIGNUP_NICK
import com.loopcat.helper.signup.SignupMailScreen
import com.loopcat.helper.signup.SignupNickScreen
import com.loopcat.helper.signup.SignupScreen
import com.loopcat.helper.splash.HelperSplashScreen
import com.loopcat.helper.splash.NAVIGATION_SPLASH

@Composable
fun AuthNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NAVIGATION_SPLASH
    ) {
        composable(NAVIGATION_SPLASH) {
            HelperSplashScreen(
                navToLogin = {
                    navController.navigate(NAVIGATION_LOGIN)
                }
            )
        }

        composable(NAVIGATION_LOGIN) {
            LoginScreen(
                navToSignUp = {
                    navController.navigate(NAVIGATION_SIGNUP)
                }
            )
        }

        composable(NAVIGATION_SIGNUP) {
            SignupScreen(
                onNext = {
                    navController.navigate(NAVIGATION_SIGNUP_MAIL)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NAVIGATION_SIGNUP_MAIL) {
            SignupMailScreen(
                onNext = {
                    navController.navigate(NAVIGATION_SIGNUP_NICK)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NAVIGATION_SIGNUP_NICK) {
            SignupNickScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
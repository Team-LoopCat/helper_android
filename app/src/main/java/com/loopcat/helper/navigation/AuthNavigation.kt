package com.loopcat.helper.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loopcat.helper.login.LoginScreen
import com.loopcat.helper.login.NAVIGATION_LOGIN
import com.loopcat.helper.signup.NAVIGATION_SIGNUP
import com.loopcat.helper.signup.NAVIGATION_SIGNUP_MAIL
import com.loopcat.helper.signup.NAVIGATION_SIGNUP_NICK
import com.loopcat.helper.signup.SignupMailScreen
import com.loopcat.helper.signup.SignupNickScreen
import com.loopcat.helper.signup.SignupScreen

@Composable
fun AuthNavigation(
    navController: NavHostController = rememberNavController(),
    navToMain: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NAVIGATION_LOGIN
    ) {
        composable(NAVIGATION_LOGIN) {
            LoginScreen(
                navToSignUp = {
                    navController.navigate(NAVIGATION_SIGNUP)
                },
                navToMain = navToMain
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
                },
                navToLogin = {
                    navController.navigate(NAVIGATION_LOGIN) {
                        popUpTo(NAVIGATION_LOGIN)
                    }
                }
            )
        }
    }
}
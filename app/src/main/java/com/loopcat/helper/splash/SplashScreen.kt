package com.loopcat.helper.splash

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loopcat.helper.R
import com.loopcat.helper.splash.viewmodel.SplashViewModel
import com.loopcat.helper.ui.HelperAlertDialog
import com.loopcat.helper.ui.theme.White
import kotlinx.coroutines.delay

const val NAVIGATION_SPLASH = "splash"

@Composable
fun HelperSplashScreen(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel = viewModel(),
    navToLogin: () -> Unit,
    navToMain: () -> Unit
) {
    val alpha = remember { Animatable(0f) }
    var isException by remember { mutableStateOf(false) }
    val isLoggedIn by splashViewModel.isLoggedIn

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(500)
        )
        delay(1000L)
        try {
            when (isLoggedIn) {
                true -> navToMain()
                false -> navToLogin()
                else -> {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            }
        } catch (e: Exception) {
            Log.e("splash screen", e.toString())
            isException = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .alpha(alpha.value)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_main),
            contentDescription = "logo",
            modifier = modifier
                .align(Alignment.Center)
                .padding(60.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        if (isException) {
            HelperAlertDialog(
                title = stringResource(id = R.string.splash_exception),
                onClick = {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            )
        }
    }
}
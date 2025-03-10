package com.loopcat.helper.splash

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loopcat.helper.R
import com.loopcat.helper.ui.HelperAlertDialog
import com.loopcat.helper.ui.theme.White
import kotlinx.coroutines.delay

const val NAVIGATION_SPLASH = "splash"

@Composable
fun HelperSplashScreen(
    modifier: Modifier = Modifier,
    navToLogin: () -> Unit
) {
    val alpha = remember { Animatable(0f) }
    var isException by remember { mutableStateOf(false) }
    
    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(500)
        )
        delay(1000L)

        try {
            navToLogin()
        } catch (e: Exception) {
            println(e.message)
            isException = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
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
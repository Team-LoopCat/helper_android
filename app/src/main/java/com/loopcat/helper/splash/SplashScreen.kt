package com.loopcat.helper.splash

import android.content.Intent
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.loopcat.helper.MainActivity
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun HelperSplashScreen(modifier: Modifier = Modifier) {
    val alpha = remember { Animatable(0f) }
    val context = LocalContext.current
    
    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
        delay(1000L)

        Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }.let { intent ->
            ContextCompat.startActivity(context, intent, null)
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
    }
}
package com.loopcat.helper.ui.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.dropShadow

@SuppressLint("ModifierFactoryExtensionFunction")
fun listItemBoxModifier(
    innerPadding: PaddingValues
): Modifier {
    return Modifier
        .padding(
            start = 30.dp,
            end = 30.dp,
            bottom = 10.dp,
            top = 4.dp
        )
        .fillMaxWidth()
        .wrapContentHeight()
        .dropShadow()
        .clip(RoundedCornerShape(8.dp))
        .background(White)
        .padding(innerPadding)
}
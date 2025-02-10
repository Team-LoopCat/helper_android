package com.loopcat.helper.ui.list

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

fun Modifier.listItemBoxModifier (
    innerPadding: PaddingValues
): Modifier = this
    .padding(
        start = 30.dp,
        end = 30.dp,
        top = 4.dp,
        bottom = 10.dp
    )
    .fillMaxWidth()
    .wrapContentHeight()
    .dropShadow()
    .clip(RoundedCornerShape(8.dp))
    .background(White)
    .padding(innerPadding)
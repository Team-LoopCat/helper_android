package com.loopcat.helper.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Main100
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.utils.calculateDueDate

@Composable
fun ListItemDueDate(
    modifier: Modifier = Modifier,
    date: String
) {
    Text(
        modifier = modifier
            .width(64.dp)
            .wrapContentHeight()
            .background(
                color = Main100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                top = 3.dp,
                bottom = 3.dp
            ),
        text = "D-${calculateDueDate(date)}",
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Black
        ),
        textAlign = TextAlign.Center
    )
}
package com.loopcat.helper.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

@Composable
fun HelperButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonText: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 30.dp
            )
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Gray300,
            disabledContentColor = White,
            containerColor = Main,
            contentColor = White
        ),
        enabled = enable
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                color = White,
                fontFamily = Pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}
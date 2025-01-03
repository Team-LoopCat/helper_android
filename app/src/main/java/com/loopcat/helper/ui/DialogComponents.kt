package com.loopcat.helper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.DialogBlue
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

@Composable
fun HelperAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onClick,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = White
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(42.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Black
                    )
                )
                Spacer(modifier = modifier.height(40.dp))
                HorizontalDivider(
                    thickness = 0.4.dp,
                    color = Gray400
                )
                Text(
                    text = stringResource(id = R.string.dialog_okay),
                    style = TextStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = DialogBlue
                    ),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onClick()
                        }
                        .padding(
                            top = 12.dp,
                            bottom = 12.dp
                        )
                )
            }
        }
    }
}

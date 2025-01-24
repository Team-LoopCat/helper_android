package com.loopcat.helper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.Red
import com.loopcat.helper.ui.theme.White

@Composable
private fun HelperConfirmClick(
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit,
    onClickCancel: () -> Unit
) {
    val confirmInteractionSource = remember { MutableInteractionSource() }
    val cancelInteractionSource = remember { MutableInteractionSource() }

    HorizontalDivider(
        thickness = 0.4.dp,
        color = Gray400
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.dialog_cancel),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Red
            ),
            textAlign = TextAlign.Center,
            modifier = modifier
                .weight(1f)
                .clickable(
                    interactionSource = cancelInteractionSource,
                    indication = rememberRipple()
                ) {
                    onClickCancel()
                }
                .padding(
                    top = 14.dp,
                    bottom = 14.dp
                )
        )
        VerticalDivider(
            thickness = 0.4.dp,
            color = Gray400,
            modifier = modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
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
                .weight(1f)
                .clickable(
                    interactionSource = confirmInteractionSource,
                    indication = rememberRipple()
                ) {
                    onClickConfirm()
                }
                .padding(
                    top = 14.dp,
                    bottom = 14.dp
                )
        )
    }
}

@Composable
fun HelperAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

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
                Spacer(modifier = modifier.height(48.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Black
                    )
                )
                Spacer(modifier = modifier.height(42.dp))
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
                        .clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(bounded = true)
                        ) {
                            onClick()
                        }
                        .padding(
                            top = 14.dp,
                            bottom = 14.dp
                        )
                )
            }
        }
    }
}

@Composable
fun HelperConfirmDialog(
    modifier: Modifier = Modifier,
    title: String,
    onClickCancel: () -> Unit,
    onClickConfirm: () -> Unit,
    isDelete: Boolean = false,
    description: String = ""
) {
    Dialog(
        onDismissRequest = onClickCancel,
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
                Spacer(modifier = modifier.height(48.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Black
                    ),
                    textAlign = TextAlign.Center
                )
                if (isDelete) {
                    Spacer(modifier = modifier.height(12.dp))
                    Text(
                        text = description,
                        style = TextStyle(
                            fontFamily = Pretendard,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Gray700
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = modifier.height(42.dp))
                HelperConfirmClick(
                    onClickConfirm = onClickConfirm,
                    onClickCancel = onClickCancel
                )
            }
        }
    }
}
package com.loopcat.helper.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.singleClickEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelperTopBar(
    modifier: Modifier = Modifier,
    title: String,
    isBack: Boolean = false,
    onClick: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .drawBehind {
                drawLine(
                    color = Gray400,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 0.4.dp.toPx()
                )
            },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Gray700
                ),
                letterSpacing = 0.2.em
            )
        },
        navigationIcon = {
            if (isBack) {
                singleClickEvent { singleEvent ->
                    IconButton(
                        onClick = {
                            singleEvent.event {
                                onClick()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_topbar_back),
                            contentDescription = "TopBar Back Button"
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White,
            navigationIconContentColor = Gray700,
            titleContentColor = Gray700
        )
    )
}

@Composable
fun HelperButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonText: String,
    onClick: () -> Unit
) {
    singleClickEvent { singleEvent ->
        Button(
            onClick = {
                singleEvent.event {
                    onClick()
                }
            },
            modifier = modifier
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                    bottom = 30.dp
                )
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                disabledContentColor = White,
                disabledContainerColor = Gray300,
                contentColor = White,
                containerColor = Main
            ),
            enabled = enable
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    color = White,
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
    }
}
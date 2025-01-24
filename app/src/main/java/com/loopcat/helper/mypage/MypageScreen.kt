package com.loopcat.helper.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.loopcat.helper.R
import com.loopcat.helper.ui.HelperConfirmDialog
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Gray500
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.Red
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.dropShadow

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    var isLogout by remember { mutableStateOf(false) }
    var isOutOfMembership by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelperTopBar(title = stringResource(id = R.string.mypage))
        Spacer(modifier = modifier.height(40.dp))
        Profile(
            profileUrl = "",
            nickname = "익명이"
        )
        Spacer(modifier = modifier.height(10.dp))
        MyPageOption(
            options = listOf(
                stringResource(id = R.string.mypage_my_notice) to {
                    // 내가 작성한 글로 이동
                },
                stringResource(id = R.string.mypage_my_study) to {
                    // 내 스터디로 이동
                }
            )
        )
        MyPageOption(
            options = listOf(
                stringResource(id = R.string.mypage_profile_modify) to {
                    // 프로필 수정으로 이동
                },
                stringResource(id = R.string.mypage_password_modify) to {
                    // 비밀번호 수정으로 이동
                }
            )
        )
        MyPageOption(
            options = listOf(
                stringResource(id = R.string.mypage_logout) to {
                    isLogout = true
                }
            )
        )
        MyPageOption(
            options = listOf(
                stringResource(id = R.string.mypage_out_membership) to {
                    isOutOfMembership = true
                }
            )
        )
    }

    if (isLogout) {
        HelperConfirmDialog(
            title = stringResource(id = R.string.is_logout),
            onClickCancel = {
                isLogout = false
            },
            onClickConfirm = {
                // 로그아웃
                // 로그인 페이지로 이동
            }
        )
    }
    if (isOutOfMembership) {
        HelperConfirmDialog(
            title = stringResource(id = R.string.is_out_of_membership),
            onClickCancel = {
                isOutOfMembership = false
            },
            onClickConfirm = {
                // 회원탈퇴
                // 로그인 페이지로 이동
            },
            isDelete = true,
            description = stringResource(id = R.string.is_out_of_membership_description)
        )
    }
}

@Composable
private fun Profile(
    modifier: Modifier = Modifier,
    profileUrl: String,
    nickname: String
) {
    AsyncImage(
        modifier = modifier
            .size(120.dp)
            .border(
                width = 1.dp,
                color = Gray500,
                shape = CircleShape
            )
            .clip(CircleShape),
        model = profileUrl,
        contentDescription = "profile",
        error = painterResource(R.drawable.default_profile)
    )
    Text(
        modifier = modifier
            .padding(
                top = 6.dp
            ),
        text = nickname,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Black
        )
    )
}

@Composable
private fun MyPageOptionText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick()
            }
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
        text = text,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = if (text == stringResource(id = R.string.mypage_out_membership)) Red else Black
        ),
        textAlign = TextAlign.Start
    )
}

@Composable
fun MyPageOption(
    modifier: Modifier = Modifier,
    options: List<Pair<String, () -> Unit>>
) {
    Column(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = 18.dp
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .dropShadow()
            .clip(RoundedCornerShape(8.dp))
            .background(White)
    ) {
        options.forEachIndexed { index, (option, onClick) ->
            if (index > 0) {
                HorizontalDivider(
                    modifier = modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        )
                        .fillMaxWidth(),
                    thickness = 0.4.dp,
                    color = Gray400
                )
            }
            MyPageOptionText(
                text = option,
                onClick = onClick
            )
        }
    }
}
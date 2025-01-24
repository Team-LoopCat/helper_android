package com.loopcat.helper.ui.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.community.data.CommunityListItemData
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray500
import com.loopcat.helper.ui.theme.Gray600
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.dropShadow
import com.loopcat.helper.ui.utills.noRippleClickable
import okhttp3.internal.format

@Composable
fun CommunityListItem(
    modifier: Modifier = Modifier,
    noticeItemData: CommunityListItemData,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
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
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 12.dp
            )
            .noRippleClickable {
                onClick()
            }
    ) {
        CommunityItemContent(
            modifier = modifier.align(Alignment.TopStart),
            title = noticeItemData.title,
            content = noticeItemData.contents,
            tags = noticeItemData.tag
        )
        NoticeComment(
            modifier = modifier.align(Alignment.BottomEnd),
            numberOfComments = noticeItemData.commentCount
        )
    }
}

@Composable
private fun CommunityItemContent(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    tags: List<String>
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            text = title,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = modifier
                .padding(
                    top = 4.dp
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            text = content,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                color = Gray500
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = modifier
                .padding(
                    top = 6.dp,
                    end = 30.dp,
                    bottom = 4.dp
                )
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            for (tag in tags) {
                Text(
                    modifier = modifier
                        .padding(
                            end = 8.dp
                        )
                        .wrapContentSize(),
                    text = "#$tag",
                    style = TextStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Gray600
                    )
                )
            }
        }
    }
}

@Composable
private fun NoticeComment(
    modifier: Modifier = Modifier,
    numberOfComments: Int
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .padding(
                    end = 3.dp
                )
                .width(13.dp)
                .height(11.dp),
            painter = painterResource(id = R.drawable.icon_comment),
            colorFilter = ColorFilter.tint(Gray600),
            contentDescription = "comment icon"
        )
        Text(
            text = format("%02d", numberOfComments),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                color = Gray600
            )
        )
    }
}
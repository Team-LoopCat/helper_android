package com.loopcat.helper.ui.list.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.study.data.StudyListItemData
import com.loopcat.helper.ui.list.ListItemDueDate
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Gray600
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.dropShadow
import com.loopcat.helper.ui.utills.noRippleClickable

@Composable
fun StudyListItem(
    modifier: Modifier = Modifier,
    noticeItemData: StudyListItemData,
    onClick: () -> Unit
) {
    Box (
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
                start = 20.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 18.dp
            )
            .noRippleClickable {
                onClick()
            }
    ) {
        StudyItemContent(
            modifier = modifier.align(Alignment.TopStart),
            title = noticeItemData.title, 
            date = noticeItemData.date, 
            start = noticeItemData.start, 
            end = noticeItemData.end
        )
        ListItemDueDate(
            modifier = modifier.align(Alignment.TopEnd),
            date = noticeItemData.date
        )
        StudyItemJoinMember(
            modifier = modifier.align(Alignment.BottomEnd),
            member = noticeItemData.member
        )
    }
}

@Composable
private fun StudyItemContent(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    start: String,
    end: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier
                .padding(
                    top = 2.dp,
                    end = 64.dp
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            text = title,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = modifier
                .padding(
                    top = 5.dp
                )
                .wrapContentSize(),
            text = date,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Gray600
            )
        )
        Text(
            modifier = modifier
                .padding(
                    top = 1.dp
                )
                .wrapContentSize(),
            text = "$start ~ $end",
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Gray600
            )
        )
    }
}

@Composable
fun StudyItemJoinMember(
    modifier: Modifier = Modifier,
    member: Int
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
                .size(18.dp),
            painter = painterResource(id = R.drawable.icon_study_member),
            colorFilter = ColorFilter.tint(Gray400),
            contentScale = ContentScale.FillWidth,
            contentDescription = "study member icon"
        )
        Text(
            text = "${member}명 참여",
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = Gray600
            )
        )
    }
}
package com.loopcat.helper.ui.list.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.exam.data.ExaminationData
import com.loopcat.helper.exam.data.PerformanceAssessmentData
import com.loopcat.helper.ui.list.ListItemDueDate
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray600
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.dropShadow
import com.loopcat.helper.ui.utills.noRippleClickable
import com.loopcat.helper.utils.calculateDate
import com.loopcat.helper.utils.calculateDayOfWeek

@Composable
fun ExaminationListItem(
    modifier: Modifier = Modifier,
    examItemData: ExaminationData
) {
    ExamListItem(
        title = examItemData.subject,
        content = calculateDayOfWeek(examItemData.date),
        date = examItemData.date,
        description = "${examItemData.period}교시",
        onClick = {
            // 시험 상세 조회로 이동
        }
    )
}

@Composable
fun PerformanceAssessmentListItem(
    modifier: Modifier = Modifier,
    examItemData: PerformanceAssessmentData
) {
    ExamListItem(
        title = examItemData.title, 
        content = examItemData.subject, 
        date = examItemData.endDate, 
        description = calculateDate(examItemData.endDate),
        onClick = {
            // 수행평가 상세 조회로 이동
        }
    )
}

@Composable
private fun ExamListItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    date: String,
    description: String,
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
                top = 18.dp,
                bottom = 18.dp
            )
            .noRippleClickable {
                onClick()
            }
    ) {
        ExamItemContent(
            modifier = modifier.align(Alignment.TopStart),
            title = title,
            content = content
        )
        ListItemDueDate(
            modifier = modifier.align(Alignment.TopEnd),
            date = date
        )
        Text(
            modifier = modifier.align(Alignment.BottomEnd),
            text = description,
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
private fun ExamItemContent(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier
                .padding(
                    top = 2.dp
                )
                .wrapContentSize(),
            text = content,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = Main
            )
        )
        Text(
            modifier = modifier
                .padding(
                    top = 5.dp,
                    bottom = 2.dp
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            text = title,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
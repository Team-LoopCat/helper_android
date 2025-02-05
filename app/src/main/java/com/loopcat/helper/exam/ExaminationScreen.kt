package com.loopcat.helper.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.exam.data.ExaminationData
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.utils.calculateDayOfWeek
import com.loopcat.helper.utils.getThisSchoolYear
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Preview
@Composable
fun ExaminationScreen(modifier: Modifier = Modifier) {
    val labelList = listOf(
        R.string.exam_teacher,
        R.string.exam_datetime,
        R.string.exam_problem,
        R.string.exam_percent,
        R.string.exam_examination_content
    )
    val examinationData = ExaminationData(
        UUID.randomUUID(),
        "2025-03-02",
        1,
        "자바 프로그래밍",
        "최수장",
        20,
        30,
        "시험 범위입니당~ ^-^"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.Start
    ) {
        HelperTopBar(
            title = stringResource(id = R.string.exam_examination),
            isBack = true,
            onClick = {
                // ExamScreen으로
            }
        )
        ExamInfo(
            description = "${getThisSchoolYear()}학년도",
            title = examinationData.subject,
            labelList = labelList,
            valueList = listOf(
                "${examinationData.teacher} 선생님",
                "${calculateDayOfWeek(examinationData.date)} ${examinationData.period}교시",
                "${examinationData.problems}문제",
                "${examinationData.percent}%",
                examinationData.content
            )
        )
    }
}
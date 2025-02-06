package com.loopcat.helper.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.loopcat.helper.R
import com.loopcat.helper.exam.data.ExaminationData
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.utils.calculateDayOfWeek
import com.loopcat.helper.utils.getThisExamination
import com.loopcat.helper.utils.getThisSchoolYear
import java.util.UUID

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
            description = "${getThisSchoolYear()}학년도 " + stringResource(id = getThisExamination()),
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
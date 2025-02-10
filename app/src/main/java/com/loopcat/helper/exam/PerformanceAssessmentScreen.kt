package com.loopcat.helper.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loopcat.helper.R
import com.loopcat.helper.exam.data.PerformanceAssessmentData
import com.loopcat.helper.exam.data.PerformanceAttendData
import com.loopcat.helper.ui.DownloadFiles
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.utils.FileData
import com.loopcat.helper.utils.calculateDayOfWeek
import com.loopcat.helper.utils.classNumber
import com.loopcat.helper.utils.grade
import java.util.UUID

@Composable
fun PerformanceAssessmentScreen(modifier: Modifier = Modifier) {
    val labelList = listOf(
        R.string.exam_teacher,
        R.string.exam_end_date,
        R.string.exam_percent,
        R.string.exam_performance_content
    )
    val performanceAssessmentData = PerformanceAssessmentData(
        UUID.randomUUID(),
        "프젝 수행평가",
        "자바 프로그래밍",
        "최수장",
        "수행평가 내용내용",
        20,
        listOf(
            PerformanceAttendData(
                UUID.randomUUID(),
                "2",
                "2",
                "2024-11-03"
            ),
            PerformanceAttendData(
                UUID.randomUUID(),
                "2",
                "1",
                "2024-11-05"
            )
        ),
        listOf(
            FileData("첫 번째 파일", ""),
            FileData("두 번째 파일", ""),
            FileData("세 번째 파일", "")
        )
    )
    val myClassInfo = performanceAssessmentData.attends.filter { it.grade == grade && it.classroom == classNumber }[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.Start
    ) {
        HelperTopBar(
            title = stringResource(id = R.string.exam_performance_assessment),
            isBack = true,
            onClick = {
                // 이전 페이지로 이동
            }
        )
        ExamInfo(
            description = performanceAssessmentData.subject,
            title = performanceAssessmentData.title,
            labelList = labelList,
            valueList = listOf(
                "${performanceAssessmentData.teacher} 선생님",
                calculateDayOfWeek(myClassInfo.endDate),
                "${performanceAssessmentData.percent}%",
                performanceAssessmentData.content
            )
        )
        if (performanceAssessmentData.file.isNotEmpty()) {
            Spacer(modifier = modifier.height(30.dp))
            DownloadFiles(fileList = performanceAssessmentData.file)
        }
    }
}
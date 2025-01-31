package com.loopcat.helper.exam

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.exam.data.ExaminationData
import com.loopcat.helper.exam.data.PerformanceAssessmentData
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.list.exam.ExaminationListItem
import com.loopcat.helper.ui.list.exam.PerformanceAssessmentListItem
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White
import java.util.UUID

@Composable
fun ExamScreen(
    modifier: Modifier = Modifier
) {
    val examList = listOf(
        ExaminationData(UUID.randomUUID(), "2024-03-20", "과목", "선생님", 1, 20, 35)
    )
    val performanceList = listOf(
        PerformanceAssessmentData(UUID.randomUUID(), "제목", "과목", "2025-12-30")
    )

    var isExamination by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        HelperTopBar(title = stringResource(id = R.string.exam))
        Spacer(modifier = modifier.height(20.dp))
        ExamToggle(
            isExamination = isExamination,
            onPerformanceAssessmentSelected = {
                isExamination = false
            },
            onExaminationSelected = {
                isExamination = true
            }
        )
        Spacer(modifier = modifier.height(20.dp))
        LazyColumn {
            if (isExamination) {
                items(examList) { examItem ->
                    ExaminationListItem(examItemData = examItem)
                }
            } else {
                items(performanceList) { examItem ->
                    PerformanceAssessmentListItem(examItemData = examItem)
                }
            }
        }
    }
}

@Composable
private fun ExamToggle(
    modifier: Modifier = Modifier,
    isExamination: Boolean,
    onPerformanceAssessmentSelected: () -> Unit,
    onExaminationSelected: () -> Unit
) {
    Row (
        modifier = modifier
            .padding(
                start = 30.dp
            )
            .wrapContentSize()
    ) {
        ExamToggleItem(
            isSelected = !isExamination, 
            text = stringResource(id = R.string.exam_performance_assessment),
            onClick = onPerformanceAssessmentSelected
        )
        Spacer(modifier = modifier.width(10.dp))
        ExamToggleItem(
            isSelected = isExamination,
            text = stringResource(id = R.string.exam_examination),
            onClick = onExaminationSelected
        )
    }
}

@Composable
private fun ExamToggleItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = if (isSelected) Main else White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Main,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
            .padding(
                start = 18.dp,
                end = 18.dp,
                top = 6.dp,
                bottom = 6.dp
            ),
        text = text,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = if (isSelected) White else Main
        )
    )
}
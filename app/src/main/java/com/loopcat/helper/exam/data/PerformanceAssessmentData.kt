package com.loopcat.helper.exam.data

import com.loopcat.helper.utils.FileData
import java.util.UUID

data class PerformanceAssessmentListItemData(
    val test_id: UUID,
    val title: String,
    val subject: String,
    val endDate: String
)

data class PerformanceAssessmentData(
    val id: UUID,
    val title: String,
    val subject: String,
    val teacher: String,
    val content: String,
    val percent: Int,
    val attends: List<PerformanceAttendData>,
    val file: List<FileData>
)

data class PerformanceAttendData(
    val attendId: UUID,
    val grade: String,
    val classroom: String,
    val endDate: String
)
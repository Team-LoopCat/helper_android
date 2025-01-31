package com.loopcat.helper.exam.data

import java.util.UUID

data class PerformanceAssessmentListItemData(
    val test_id: UUID,
    val title: String,
    val subject: String,
    val endDate: String
)
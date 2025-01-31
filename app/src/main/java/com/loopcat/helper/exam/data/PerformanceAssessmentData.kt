package com.loopcat.helper.exam.data

import java.util.UUID

data class PerformanceAssessmentData(
    val test_id: UUID,
    val title: String,
    val subject: String,
    val endDate: String
)
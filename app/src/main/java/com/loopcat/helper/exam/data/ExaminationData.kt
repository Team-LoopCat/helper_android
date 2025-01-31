package com.loopcat.helper.exam.data

import java.util.UUID

data class ExaminationData(
    val examDataId: UUID,
    val date: String,
    val subject: String,
    val teacher: String,
    val period: Int,
    val problems: Int,
    val percent: Int
)
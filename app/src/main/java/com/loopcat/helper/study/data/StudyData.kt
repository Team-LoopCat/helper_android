package com.loopcat.helper.study.data

import java.util.UUID

data class StudyListItemData(
    val studyId: UUID,
    val title: String,
    val member: Int,
    val date: String,
    val start: String,
    val end: String
)

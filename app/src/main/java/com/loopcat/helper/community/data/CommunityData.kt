package com.loopcat.helper.community.data

import java.util.UUID

data class CommunityListItemData(
    val postId: UUID,
    val title: String,
    val contents: String,
    val tag: List<String>,
    val commentCount: Int
)
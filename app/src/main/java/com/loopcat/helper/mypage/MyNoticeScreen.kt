package com.loopcat.helper.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.loopcat.helper.R
import com.loopcat.helper.community.data.CommunityListItemData
import com.loopcat.helper.ui.notice.CommunityListItem
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.theme.White
import java.util.UUID

@Composable
fun MyNoticeScreen(
    modifier: Modifier = Modifier
) {
    val myNotice: List<CommunityListItemData> = listOf(
        CommunityListItemData(UUID.randomUUID(), "신문에 TV에 월급봉투에~", "아파트 담벼락보다는 바달 볼 수 있는 창문이 좋아요, 깡깡", listOf("제주도", "푸른_바다", "아니_푸른_밤_이였던가"), 10),
        CommunityListItemData(UUID.randomUUID(), "신문에 TV에 월급봉투에~", "아파트 담벼락보다는 바달 볼 수 있는 창문이 좋아요, 깡깡", listOf("제주도", "푸른_바다", "아니_푸른_밤_이였던가"), 4)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        HelperTopBar(
            title = stringResource(id = R.string.mypage_my_notice),
            isBack = true,
            onClick = {
                // 마이페이지로
            }
        )
        Spacer(modifier = modifier.height(16.dp))
        LazyColumn {
            items(myNotice) { notice ->
                CommunityListItem(
                    noticeItemData = notice,
                    onClick = {
                        // 상세 조회로 이동
                    }
                )
            }
        }
    }
}
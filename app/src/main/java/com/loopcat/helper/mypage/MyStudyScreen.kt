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
import com.loopcat.helper.study.data.StudyListItemData
import com.loopcat.helper.ui.HelperTopBar
import com.loopcat.helper.ui.list.StudyListItem
import com.loopcat.helper.ui.theme.White
import java.util.UUID

@Composable
fun MyStudyScreen(modifier: Modifier = Modifier) {
    val myStudy: List<StudyListItemData> = listOf(
        StudyListItemData(UUID.randomUUID(), "주말에 토익 공부 같이 하실 분", 20, "2025-01-31", "20:00", "22:30"),
        StudyListItemData(UUID.randomUUID(), "스터디 제목인데 조금 더 길었으면 좋겠다~~아아아아아아아아아앙", 4, "2025-02-24", "10:00", "12:30")
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        HelperTopBar(
            title = stringResource(id = R.string.mypage_my_study),
            isBack = true,
            onClick = {
                // 마이페이지로
            }
        )
        Spacer(modifier = modifier.height(16.dp))
        LazyColumn {
            items(myStudy) { notice ->
                StudyListItem(
                    noticeItemData = notice,
                    onClick = {
                        // 상세 페이지로 이동
                    }
                )
            }
        }
    }
}
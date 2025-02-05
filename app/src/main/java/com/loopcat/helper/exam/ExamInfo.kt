package com.loopcat.helper.exam

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard

@Composable
fun ExamInfo(
    modifier: Modifier = Modifier,
    description: String,
    title: String,
    labelList: List<Int>,
    valueList: List<String>
) {
    val lastIndex = labelList.size - 1

    Column (
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = modifier.height(40.dp))
        ExamInfoTitle(
            description = description,
            title = title
        )
        Spacer(modifier = modifier.height(30.dp))
        for (i in 0 until lastIndex) {
            ExamInfoItem(
                label = stringResource(labelList[i]),
                value = valueList[i]
            )
        }
        ExamInfoContent(
            label = stringResource(id = labelList[lastIndex]),
            content = valueList[lastIndex]
        )
    }
}

@Composable
private fun ExamInfoTitle(
    modifier: Modifier = Modifier,
    description: String,
    title: String
) {
    Column {
        Text(
            text = description,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Main
            )
        )
        Text(
            modifier = modifier
                .padding(
                    top = 4.dp
                )
                .fillMaxWidth(),
            text = title,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Black
            )
        )
    }
}

@Composable
private fun ExamInfoItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Text(
            modifier = modifier
                .width(100.dp),
            text = label,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Gray700
            )
        )
        Text(
            text = value,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Black
            )
        )
    }
}

@Composable
private fun ExamInfoContent(
    modifier: Modifier = Modifier,
    label: String,
    content: String
) {
    Column (
        modifier = modifier
            .padding(
                top = 14.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Gray700
            )
        )
        Text(
            modifier = modifier
                .padding(
                    top = 4.dp
                )
                .fillMaxWidth(),
            text = content,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Black
            )
        )
    }
}


package com.loopcat.helper.ui

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray100
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.utills.noRippleClickable
import com.loopcat.helper.utils.FileData

@Composable
private fun DownloadFileItem(
    modifier: Modifier = Modifier,
    fileName: String,
    fileUrl: String,
    context: Context
) {
    val downloadManager = remember { context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager }

    Row (
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                val request = DownloadManager
                    .Request(Uri.parse(fileUrl))
                    .setTitle(fileName)
                    .setDescription("파일을 다운로드 중입니다")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalFilesDir(
                        context,
                        Environment.DIRECTORY_DOWNLOADS,
                        fileName
                    )

                downloadManager.enqueue(request)
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier
                .weight(1f)
                .padding(
                    end = 18.dp
                ),
            text = fileName,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Image(
            modifier = modifier.size(16.dp),
            painter = painterResource(id = R.drawable.icon_download),
            contentDescription = "Download Icon"
        )
    }
}

@Composable
private fun DownloadFilesContent(
    modifier: Modifier = Modifier,
    fileList: List<FileData>
) {
    val context = LocalContext.current
    
    Column (
        modifier = modifier
            .padding(
                top = 2.dp
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Gray100,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 18.dp,
                bottom = 18.dp
            )
    ) {
        fileList.forEachIndexed { index, fileData -> 
            if (index > 0) {
                Spacer(modifier = modifier.height(16.dp))
            }
            DownloadFileItem(
                fileName = fileData.fileName, 
                fileUrl = fileData.url, 
                context = context
            )
        }
    }
}

@Composable
fun DownloadFiles(
    modifier: Modifier = Modifier,
    fileList: List<FileData>
) {
    Column (
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = stringResource(id = R.string.file),
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Gray700
            )
        )
        DownloadFilesContent(fileList = fileList)
    }
}
package com.loopcat.helper.ui.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.loopcat.helper.R
import com.loopcat.helper.ui.InputPlaceHolder
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray500
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Main100
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.Red
import com.loopcat.helper.ui.theme.White
import com.loopcat.helper.ui.utills.noRippleClickable
import com.loopcat.helper.ui.utills.singleClickEvent

@Composable
fun AuthTitle(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.ExtraBold,
            color = Black,
            fontSize = 30.sp
        )
    )
}

@Composable
fun AuthErrorMessage(
    modifier: Modifier = Modifier,
    errorType: AuthErrorType
) {
    Text(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 4.dp,
                top = 2.dp
            ),
        text = stringResource(id = authErrorMessage(errorType)),
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Red
        )
    )
}

@Composable
private fun SmallInput(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    
    Box(
        modifier = modifier
            .fillMaxWidth(
                fraction = 0.73f
            )
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isFocused) Main else Gray300,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                start = 18.dp,
                end = 18.dp,
                top = 16.dp,
                bottom = 16.dp
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        BasicTextField(
            modifier = modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth(),
            value = input,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Black
            ),
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if (input.isEmpty()) {
                    InputPlaceHolder(hint = hint)
                }
                innerTextField()
            }
        )
    }
}

@Composable
private fun SmallButton(
    modifier: Modifier = Modifier,
    enable: Boolean,
    buttonText: String,
    onClick: () -> Unit
) {
    singleClickEvent { singleEvent ->
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                singleEvent.event {
                    onClick()
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                disabledContentColor = White,
                contentColor = White,
                disabledContainerColor = Gray300,
                containerColor = Main100
            ),
            enabled = enable,
            contentPadding = PaddingValues(
                horizontal = 12.dp,
                vertical = 14.dp
            )
        ) {
            Text(
                text = buttonText,
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = White
                )
            )
        }
    }
}

@Composable
fun SmallInputWithButton(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    enable: Boolean,
    buttonText: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallInput(
            input = input,
            hint = hint,
            onValueChange = onValueChange
        )
        Spacer(modifier = modifier.width(6.dp))
        SmallButton(
            enable = enable,
            buttonText = buttonText,
            onClick = onClick
        )
    }
}

@Composable
fun ProfileModify(
    modifier: Modifier = Modifier,
    imageUrl: Uri,
    onImageSelected: (Uri?) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri)
    }
    Box(
        modifier = modifier
            .size(120.dp)
            .noRippleClickable {
                launcher.launch("image/*")
            }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Profile Image",
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Gray500,
                    shape = CircleShape
                )
        )
        Image(
            painter = painterResource(id = R.drawable.icon_pencil),
            contentDescription = "Profile Modify",
            modifier = modifier
                .padding(
                    end = 8.dp
                )
                .align(Alignment.BottomEnd)
                .size(28.dp)
                .background(
                    color = White,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Gray500,
                    shape = CircleShape
                )
                .padding(7.dp)
        )
    }
}
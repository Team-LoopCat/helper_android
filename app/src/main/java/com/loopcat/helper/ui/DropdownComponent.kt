package com.loopcat.helper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.loopcat.helper.R
import com.loopcat.helper.ui.theme.Black
import com.loopcat.helper.ui.theme.Gray300
import com.loopcat.helper.ui.theme.Gray400
import com.loopcat.helper.ui.theme.Gray700
import com.loopcat.helper.ui.theme.Main
import com.loopcat.helper.ui.theme.Pretendard
import com.loopcat.helper.ui.theme.White

@Composable
fun LargeDropdownHeader(
    modifier: Modifier = Modifier,
    isCategory: Boolean,
    selectedOption: String,
    isExpanded: Boolean,
    onGloballyPositioned: (LayoutCoordinates) -> Unit,
    onHeaderClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(
                start = 30.dp,
                end = 30.dp
            )
            .fillMaxWidth()
            .background(
                color = White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = if (isCategory) Main else Gray300,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onHeaderClick()
            }
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 14.dp,
                bottom = 14.dp
            )
            .onGloballyPositioned { coordinates ->
                onGloballyPositioned(coordinates)
            }
    ) {
        Text(
            text = selectedOption,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = if (isCategory) Main else Black
            ),
            modifier = modifier.align(Alignment.CenterStart)
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_dropdown),
            contentDescription = "Dropdown Icon",
            tint = if (isCategory) Main else Gray700,
            modifier = modifier
                .align(Alignment.CenterEnd)
                .rotate(if (isExpanded) 180f else 0f)
        )
    }
}

@Composable
fun LargeDropdownMenuItem(
    modifier: Modifier = Modifier,
    index: Int,
    option: String,
    onOptionClick: () -> Unit
) {
    if (index != 0) {
        HorizontalDivider(
            thickness = 0.6.dp,
            color = Gray400
        )
    }
    Text(
        text = option,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onOptionClick()
            }
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 12.dp
            )
    )
}

@Composable
fun LargeDropdownMenu(
    modifier: Modifier = Modifier,
    offset: IntOffset,
    options: List<String>,
    onDismissRequest: () -> Unit,
    onOptionClick: (String) -> Unit
) {
    Popup(
        alignment = Alignment.TopCenter,
        offset = offset,
        onDismissRequest = onDismissRequest
    ) {
        LazyColumn(
            modifier = modifier
                .padding(
                    start = 30.dp,
                    end = 30.dp
                )
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = White,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 0.6.dp,
                    color = Gray400,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            itemsIndexed(options) { index, option ->
                LargeDropdownMenuItem(
                    index = index,
                    option = option,
                    onOptionClick = {
                        onOptionClick(option)
                    }
                )
            }
        }
    }
}

@Composable
fun LargeDropdown(
    isCategory: Boolean,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isHeaderClicked by remember { mutableStateOf(false) }

    var headerHeight by remember { mutableIntStateOf(0) }

    Column {
        LargeDropdownHeader(
            isCategory = isCategory,
            selectedOption = selectedOption,
            isExpanded = isExpanded,
            onGloballyPositioned = { coordinates ->
                headerHeight = coordinates.size.height
            },
            onHeaderClick = {
                isExpanded = !isExpanded
                isHeaderClicked = true
            }
        )

        if (isExpanded) {
            LargeDropdownMenu(
                offset = IntOffset(
                    x = 0,
                    y = headerHeight + with(LocalDensity.current) { 30.dp.toPx() }.toInt()
                ),
                options = options,
                onDismissRequest = {
                    if (!isHeaderClicked) {
                        isExpanded = false
                    }
                    isHeaderClicked = false
                },
                onOptionClick = { option ->
                    onOptionSelected(option)
                    isExpanded = false
                }
            )
        }
    }
}

@Composable
fun SmallDropdownHeader(
    modifier: Modifier = Modifier,
    selectedOption: String,
    isExpanded: Boolean,
    onGloballyPositioned: (LayoutCoordinates) -> Unit,
    onHeaderClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(140.dp)
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Main,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onHeaderClick()
            }
            .padding(
                end = 14.dp,
                top = 10.dp,
                bottom = 10.dp
            )
            .onGloballyPositioned { coordinates ->
                onGloballyPositioned(coordinates)
            }
    ) {
        Text(
            text = selectedOption,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Main
            ),
            modifier = modifier
                .align(Alignment.Center)
                .padding(
                    end = 12.dp
                )
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_dropdown),
            contentDescription = "Dropdown Icon",
            tint = Main,
            modifier = modifier
                .width(14.dp)
                .align(Alignment.CenterEnd)
                .rotate(if (isExpanded) 180f else 0f)
        )
    }
}

@Composable
fun SmallDropdownMenuItem(
    modifier: Modifier = Modifier,
    index: Int,
    option: String,
    onOptionClick: () -> Unit
) {
    if (index != 0) {
        HorizontalDivider(
            thickness = 0.4.dp,
            color = Gray400
        )
    }
    Text(
        text = option,
        style = TextStyle(
            fontFamily = Pretendard,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Black,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .width(140.dp)
            .wrapContentHeight()
            .clickable {
                onOptionClick()
            }
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
    )
}

@Composable
fun SmallDropdownMenu(
    modifier: Modifier = Modifier,
    offset: IntOffset,
    options: List<String>,
    onDismissRequest: () -> Unit,
    onOptionClick: (String) -> Unit
) {
    Popup(
        alignment = Alignment.TopCenter,
        offset = offset,
        onDismissRequest = onDismissRequest
    ) {
        LazyColumn(
            modifier = modifier
                .width(140.dp)
                .wrapContentHeight()
                .background(
                    color = White,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 0.4.dp,
                    color = Gray400,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            itemsIndexed(options) { index, option ->
                SmallDropdownMenuItem(
                    index = index,
                    option = option,
                    onOptionClick = {
                        onOptionClick(option)
                    }
                )
            }
        }
    }
}

@Composable
fun SmallDropdown(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isHeaderClicked by remember { mutableStateOf(false) }

    var headerHeight by remember { mutableIntStateOf(0) }

    Column {
        SmallDropdownHeader(
            selectedOption = selectedOption,
            isExpanded = isExpanded,
            onGloballyPositioned = { coordinates ->
                headerHeight = coordinates.size.height
            },
            onHeaderClick = {
                isExpanded = !isExpanded
                isHeaderClicked = true
            }
        )

        if (isExpanded) {
            SmallDropdownMenu(
                offset = IntOffset(
                    x = 0,
                    y = headerHeight + with(LocalDensity.current) { 22.dp.toPx() }.toInt()
                ),
                options = options,
                onDismissRequest = {
                    if (!isHeaderClicked) {
                        isExpanded = false
                    }
                    isHeaderClicked = false
                },
                onOptionClick = { option ->
                    onOptionSelected(option)
                    isExpanded = false
                }
            )
        }
    }
}
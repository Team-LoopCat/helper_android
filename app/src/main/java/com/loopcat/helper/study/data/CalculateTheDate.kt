package com.loopcat.helper.study.data

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun calculateTheDate(date: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val endDate = dateFormat.parse(date)!!.time
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    return (endDate - today) / (24 * 60 * 60 * 1000)
}
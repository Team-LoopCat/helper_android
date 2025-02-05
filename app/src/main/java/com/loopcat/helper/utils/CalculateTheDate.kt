package com.loopcat.helper.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun calculateDueDate(date: String): Long {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val parseDate = dateFormat.parse(date) ?: return 0
    val endDate = parseDate.time
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time.time

    return (endDate - today) / (24 * 60 * 60 * 1000)
}

@SuppressLint("SimpleDateFormat")
fun calculateDate(date: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val parseDate = dateFormat.parse(date) ?: date
    return dateFormat.format(parseDate)
}

@SuppressLint("SimpleDateFormat")
fun calculateDayOfWeek(date: String): String {
    val targetDateFormat = SimpleDateFormat("yyyy-MM-dd (E)")
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val parseDate = dateFormat.parse(date) ?: date
    return targetDateFormat.format(parseDate)
}

fun getThisSchoolYear(): Int {
    val calendar = Calendar.getInstance()
    if (calendar.get(Calendar.MONTH) <= 2) {
        return calendar.get(Calendar.YEAR) - 1
    }
    return calendar.get(Calendar.YEAR)
}
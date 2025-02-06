package com.loopcat.helper.utils

import android.util.Log
import com.loopcat.helper.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun calculateDueDate(date: String): Long {
    val endDate = LocalDate.parse(date)
    val today = LocalDate.now()
    return ChronoUnit.DAYS.between(today, endDate)
}

fun calculateDate(date: String): String {
    return try {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date).format(dateFormat)
    } catch (e: Exception) {
        Log.e("calculateDate", e.message.toString())
        date
    }
}

fun calculateDayOfWeek(date: String): String {
    return try {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dayDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd (E)")
        LocalDate.parse(date, dateFormat).format(dayDateFormat)
    } catch (e: Exception) {
        Log.e("calculateDayOfWeek", e.message.toString())
        date
    }
}

fun getThisSchoolYear(): Int {
    val date = LocalDate.now()
    return if (date.monthValue < 3) {
        date.year - 1
    } else {
        date.year
    }
}

fun getThisExamination(): Int {
    val thisMonth = LocalDate.now().monthValue
    return if (thisMonth in 3..5 || thisMonth in 9..10) {
        R.string.exam_mid_exam
    } else {
        R.string.exam_final_exam
    }
}
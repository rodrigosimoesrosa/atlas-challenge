package br.com.atlas.extension

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by rodrigosimoesrosa on 15/01/18.
 * Copyright © 2019. All rights reserved.
 */
const val DEFAULT = "yyyy-MM-dd"
const val ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"

fun String.toISO8601UTC(locale: Locale? = null): Calendar {
    val simpleDateFormat = SimpleDateFormat(ISO8601, locale ?: Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.time = simpleDateFormat.parse(this)
    return calendar
}

fun Calendar.byLocale(locale: Locale? = null): String {
    val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale ?: Locale.getDefault())
    return dateFormat.format(time)
}

fun Calendar.toDefault(locale: Locale? = null): String {
    val simpleDateFormat = SimpleDateFormat(DEFAULT, locale ?: Locale.getDefault())
    return simpleDateFormat.format(time)
}

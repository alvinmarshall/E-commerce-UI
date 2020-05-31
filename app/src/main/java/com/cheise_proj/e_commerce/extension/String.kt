package com.cheise_proj.e_commerce.extension

import java.text.SimpleDateFormat
import java.util.*

fun String?.hideCardDigit(): String? {
    return this?.apply {
        if (this.length > 12) {
            return this.replaceRange(IntRange(0, 11), "**** **** **** ")
        }
    }
}

fun String?.toSimpleDate(): String? {
    return this?.apply {
        val simpleDateFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
        return simpleDateFormat.format(Date(this.toLong()))
    }
}
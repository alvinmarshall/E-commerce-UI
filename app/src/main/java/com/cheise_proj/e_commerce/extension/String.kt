package com.cheise_proj.e_commerce.extension

fun String?.hideCardDigit(): String? {
    return this?.apply {
        if (this.length > 12) {
           return this.replaceRange(IntRange(0, 11), "**** **** **** ")
        }
    }
}
package com.cheise_proj.e_commerce.data.service

import android.content.Context
import androidx.core.content.ContextCompat
import com.cheise_proj.e_commerce.R


object FilterService {
    fun getSizesOpts(): Array<String> {
        return arrayOf("XS", "S", "M", "L", "XL")
    }

    fun getCategoryOpts(): Array<String> {
        return arrayOf(
            "All",
            "Women",
            "Men",
            "Boys",
            "Girls"
        )
    }

    fun getBrandOpts(): Array<String> {
        return arrayOf(
            "adidas",
            "adidas original",
            "Blend",
            "Boutique Moschino",
            "Champion",
            "Diesel",
            "Jack & Jones",
            "Naf Naf",
            "Red Valentino",
            "s.Oliver"
        )
    }

    fun getColorOpts(context: Context): Array<Int> {
        return arrayOf(
            ContextCompat.getColor(context, R.color.color_1),
            ContextCompat.getColor(context, R.color.color_2),
            ContextCompat.getColor(context, R.color.color_3),
            ContextCompat.getColor(context, R.color.color_4),
            ContextCompat.getColor(context, R.color.color_4)
        )
    }


}
package com.cheise_proj.e_commerce.data.service

import com.cheise_proj.e_commerce.model.BrandOpts
import com.cheise_proj.e_commerce.model.CategoryOpts

object FilterService {
    fun getSizesOpts(): List<String> {
        return arrayListOf("XS", "S", "M", "L", "XL")
    }

    fun getCategoryOpts(): List<CategoryOpts> {
        return arrayListOf(
            CategoryOpts(1, "All"),
            CategoryOpts(2, "Women"),
            CategoryOpts(3, "Men"),
            CategoryOpts(4, "Boys"),
            CategoryOpts(5, "Girls")
        )
    }

    fun getBrandOpts(): List<BrandOpts> {
        return arrayListOf(
            BrandOpts(1, "adidas"),
            BrandOpts(2, "adidas original"),
            BrandOpts(3, "Blend"),
            BrandOpts(4, "Boutique Moschino"),
            BrandOpts(5, "Champion"),
            BrandOpts(6, "Diesel"),
            BrandOpts(7, "Jack & Jones"),
            BrandOpts(8, "Naf Naf"),
            BrandOpts(9, "Red Valentino"),
            BrandOpts(10, "s.Oliver")
        )
    }


}
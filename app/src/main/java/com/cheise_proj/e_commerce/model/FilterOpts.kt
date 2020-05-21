package com.cheise_proj.e_commerce.model

data class FilterOpts(
    val title: String,
    val viewType:Int
)

data class PriceRange(val id: Int, val min: Int, val max: Int)
data class CategoryOpts(val id: Int, val title: String)
data class BrandOpts(val id: Int, val title: String)
package com.cheise_proj.e_commerce.model.dto


import com.google.gson.annotations.SerializedName

data class CategoryDto(
    val data: List<CategoryData>
)

data class CategoryData(
    @SerializedName("CategoryID")
    val categoryID: String,
    @SerializedName("CategoryName")
    val categoryName: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("ImageUrl")
    val imageUrl: String
)
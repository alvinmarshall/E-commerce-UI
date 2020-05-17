package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.data.mapper.CategoryDtoModelMapper
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.dto.CategoryData

fun Category.toDto() = CategoryDtoModelMapper().modelToT(this)
fun CategoryData.toModel() = CategoryDtoModelMapper().tToModel(this)

fun List<Category>.toDtoList(): List<CategoryData> = CategoryDtoModelMapper().mListToT(this)
fun List<CategoryData>.toModelList(): List<Category> = CategoryDtoModelMapper().tListToModel(this)
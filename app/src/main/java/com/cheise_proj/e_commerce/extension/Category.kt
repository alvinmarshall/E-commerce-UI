package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.data.db.entity.CategoryEntity
import com.cheise_proj.e_commerce.data.mapper.CategoryDtoModelMapper
import com.cheise_proj.e_commerce.data.mapper.CategoryEntityModelMapper
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.dto.CategoryData

fun Category.toDto() = CategoryDtoModelMapper().modelToT(this)
fun CategoryData.toModel() = CategoryDtoModelMapper().tToModel(this)

fun List<Category>.toDtoList(): List<CategoryData> = CategoryDtoModelMapper().mListToT(this)
fun List<CategoryData>.toModelList(): List<Category> = CategoryDtoModelMapper().tListToModel(this)

fun Category.toEntity() = CategoryEntityModelMapper().tToModel(this)
fun CategoryEntity.toCategory() = CategoryEntityModelMapper().modelToT(this)

fun List<Category>.toEntityList() = CategoryEntityModelMapper().tListToModel(this)
fun List<CategoryEntity>.toCategoryList() = CategoryEntityModelMapper().mListToT(this)
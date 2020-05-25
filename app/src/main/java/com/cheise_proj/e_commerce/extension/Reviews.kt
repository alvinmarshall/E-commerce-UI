package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.data.db.entity.ReviewEntity
import com.cheise_proj.e_commerce.data.mapper.ReviewDtoModelMapper
import com.cheise_proj.e_commerce.data.mapper.ReviewEntityMapper
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.model.dto.ReviewData


fun ReviewData.toModel() = ReviewDtoModelMapper().tToModel(this)
fun Reviews.toDto() = ReviewDtoModelMapper().modelToT(this)

fun List<ReviewData>.toModelList() = ReviewDtoModelMapper().tListToModel(this)
fun List<Reviews>.toDtoList() = ReviewDtoModelMapper().mListToT(this)

fun List<ReviewEntity>.toReviewList(): List<Reviews> = ReviewEntityMapper().tListToModel(this)
fun List<Reviews>.toEntityList(): List<ReviewEntity> = ReviewEntityMapper().mListToT(this)


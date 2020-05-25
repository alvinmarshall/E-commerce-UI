package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.data.db.entity.ReviewRatingEntity
import com.cheise_proj.e_commerce.data.mapper.ReviewRatingDtoModelMapper
import com.cheise_proj.e_commerce.data.mapper.ReviewRatingEntityModelMapper
import com.cheise_proj.e_commerce.model.ReviewRating
import com.cheise_proj.e_commerce.model.dto.ReviewRatingData

fun ReviewRatingData.toModel() = ReviewRatingDtoModelMapper().tToModel(this)
fun ReviewRating.toDto() = ReviewRatingDtoModelMapper().modelToT(this)


fun ReviewRatingEntity.toReviewRating() = ReviewRatingEntityModelMapper().tToModel(this)
fun ReviewRating.toEntity() = ReviewRatingEntityModelMapper().modelToT(this)
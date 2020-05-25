package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.model.ReviewRating
import com.cheise_proj.e_commerce.model.dto.ReviewRatingData
import com.cheise_proj.e_commerce.utils.IDataMapper

class ReviewRatingDtoModelMapper : IDataMapper<ReviewRatingData, ReviewRating> {
    override fun tToModel(t: ReviewRatingData): ReviewRating {
        return ReviewRating(
            type = t.type,
            bestRating = t.bestRating,
            ratingValue = t.ratingValue,
            worstRating = t.worstRating
        )
    }

    override fun modelToT(m: ReviewRating): ReviewRatingData {
        return ReviewRatingData(
            type = m.type,
            bestRating = m.bestRating,
            ratingValue = m.ratingValue,
            worstRating = m.worstRating
        )
    }
}
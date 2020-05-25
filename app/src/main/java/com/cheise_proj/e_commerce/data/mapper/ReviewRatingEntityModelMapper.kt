package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.data.db.entity.ReviewRatingEntity
import com.cheise_proj.e_commerce.model.ReviewRating
import com.cheise_proj.e_commerce.utils.IDataMapper

class ReviewRatingEntityModelMapper : IDataMapper<ReviewRatingEntity, ReviewRating> {
    override fun tToModel(t: ReviewRatingEntity): ReviewRating {
        return ReviewRating(
            type = t.type,
            worstRating = t.worstRating,
            ratingValue = t.ratingValue,
            bestRating = t.bestRating
        )
    }

    override fun modelToT(m: ReviewRating): ReviewRatingEntity {
        return ReviewRatingEntity(
            type = m.type,
            bestRating = m.bestRating,
            ratingValue = m.ratingValue,
            worstRating = m.worstRating
        )
    }
}
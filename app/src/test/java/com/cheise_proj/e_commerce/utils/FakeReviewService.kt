package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.model.ReviewRating
import com.cheise_proj.e_commerce.model.Reviews

object FakeReviewService {
    fun getReviews(): Reviews {
        return Reviews(
            id = 1,
            name = "any_name",
            productId = "1",
            description = "any_description",
            avatarUrl = "any_avatar_url",
            author = "any_author",
            datePublished = "any_date_published",
            images = arrayListOf(),
            reviewRating = ReviewRating(
                "@rating",
                bestRating = 1,
                ratingValue = 1,
                worstRating = 1
            ),
            type = "@review"
        )
    }
}
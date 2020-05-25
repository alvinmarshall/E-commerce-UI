package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.data.db.entity.ReviewEntity
import com.cheise_proj.e_commerce.extension.toEntity
import com.cheise_proj.e_commerce.extension.toReviewRating
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.utils.IDataListMapper

class ReviewEntityMapper : IDataListMapper<ReviewEntity, Reviews> {
    override fun tToModel(t: ReviewEntity): Reviews {
        return Reviews(
            id = t.id,
            description = t.description,
            productId = t.productId,
            avatarUrl = t.avatarUrl,
            name = t.name,
            author = t.author,
            datePublished = t.datePublished,
            images = t.images,
            type = t.type,
            reviewRating = t.reviewRating.toReviewRating()
        )
    }

    override fun modelToT(m: Reviews): ReviewEntity {
        return ReviewEntity(
            id = m.id,
            type = m.type,
            reviewRating = m.reviewRating.toEntity(),
            images = m.images,
            datePublished = m.datePublished,
            author = m.author,
            name = m.name,
            avatarUrl = m.avatarUrl,
            productId = m.productId,
            description = m.description
        )
    }

    override fun tListToModel(tList: List<ReviewEntity>): List<Reviews> {
        val data = arrayListOf<Reviews>()
        tList.forEach { review ->
            data.add(tToModel(review))
        }
        return data
    }

    override fun mListToT(mList: List<Reviews>): List<ReviewEntity> {
        val data = arrayListOf<ReviewEntity>()
        mList.forEach { review ->
            data.add(modelToT(review))
        }
        return data
    }
}
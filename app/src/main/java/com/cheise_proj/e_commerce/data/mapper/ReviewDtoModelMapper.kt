package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.extension.toDto
import com.cheise_proj.e_commerce.extension.toModel
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.model.dto.ReviewData
import com.cheise_proj.e_commerce.utils.IDataListMapper

class ReviewDtoModelMapper : IDataListMapper<ReviewData, Reviews> {
    override fun tToModel(t: ReviewData): Reviews {
        return Reviews(
            type = t.type,
            description = t.description,
            productId = t.productId,
            avatarUrl = t.avatarUrl,
            name = t.name,
            author = t.author,
            datePublished = t.datePublished,
            images = t.images,
            reviewRating = t.reviewRating.toModel(),
            id = 0
        )
    }

    override fun modelToT(m: Reviews): ReviewData {
        return ReviewData(
            type = m.type,
            reviewRating = m.reviewRating.toDto(),
            images = m.images,
            datePublished = m.datePublished,
            author = m.author,
            name = m.name,
            avatarUrl = m.avatarUrl,
            productId = m.productId,
            description = m.description
        )
    }

    override fun tListToModel(tList: List<ReviewData>): List<Reviews> {
        val data = arrayListOf<Reviews>()
        tList.forEach { review ->
            data.add(tToModel(review))
        }
        return data
    }

    override fun mListToT(mList: List<Reviews>): List<ReviewData> {
        val data = arrayListOf<ReviewData>()
        mList.forEach { review ->
            data.add(modelToT(review))
        }
        return data
    }
}
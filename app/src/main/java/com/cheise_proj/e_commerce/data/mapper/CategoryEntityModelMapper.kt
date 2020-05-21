package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.data.db.entity.CategoryEntity
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.utils.IDataListMapper

class CategoryEntityModelMapper : IDataListMapper<Category, CategoryEntity> {
    override fun tToModel(t: Category): CategoryEntity {
        return CategoryEntity(
            categoryID = t.categoryID,
            description = t.description,
            categoryName = t.categoryName,
            imageUrl = t.imageUrl,
            productId = 0
        )
    }

    override fun modelToT(m: CategoryEntity): Category {
        return Category(
            categoryID = m.categoryID,
            imageUrl = m.imageUrl,
            categoryName = m.categoryName,
            description = m.description,
            product = m.product
        )
    }

    override fun tListToModel(tList: List<Category>): List<CategoryEntity> {
        val data = arrayListOf<CategoryEntity>()
        tList.forEach { category ->
            data.add(tToModel(category))
        }
        return data
    }

    override fun mListToT(mList: List<CategoryEntity>): List<Category> {
        val data = arrayListOf<Category>()
        mList.forEach { entity ->
            data.add(modelToT(entity))
        }
        return data
    }
}
package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.dto.CategoryData
import com.cheise_proj.e_commerce.utils.IDataListMapper

class CategoryDtoModelMapper : IDataListMapper<CategoryData, Category> {
    override fun tToModel(t: CategoryData): Category {
        return Category(
            categoryID = t.categoryID,
            imageUrl = t.imageUrl,
            description = t.description,
            categoryName = t.categoryName,
            product = emptyList()
        )
    }

    override fun modelToT(m: Category): CategoryData {
        return CategoryData(
            categoryID = m.categoryID,
            imageUrl = m.imageUrl,
            description = m.description,
            categoryName = m.categoryName
        )
    }

    override fun tListToModel(tList: List<CategoryData>): List<Category> {
        val data = arrayListOf<Category>()
        tList.forEach { categoryData ->
            data.add(tToModel(categoryData))
        }
        return data
    }

    override fun mListToT(mList: List<Category>): List<CategoryData> {
        val data = arrayListOf<CategoryData>()
        mList.forEach { category ->
            data.add(modelToT(category))
        }
        return data
    }
}
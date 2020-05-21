package com.cheise_proj.e_commerce.utils

import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product

object FakeProductService {
    fun getProduct(): List<Product> {
        return arrayListOf(
            Product(
                productID = "any_product_id",
                imageUrl = "any_image_url",
                categoryID = "any_category_id",
                unitsOnOrder = "any_order",
                unitsInStock = "any_stock",
                unitPrice = "any_price",
                supplierID = "any_supplier_id",
                reorderLevel = "any_level",
                quantityPerUnit = "any_qty",
                productName = "any_product_name",
                discontinued = "any_discontinued"
            )

        )
    }


    fun getCategories(): List<Category> {
        return arrayListOf(
            Category(
                categoryID = "any_category_id",
                imageUrl = "any_image_url",
                categoryName = "any_category_name",
                description = "any_description",
                product = getProduct()
            )
        )
    }
}
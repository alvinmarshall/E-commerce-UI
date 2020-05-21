package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.model.dto.ProductData
import com.cheise_proj.e_commerce.utils.IDataListMapper

internal class ProductDtoModelMapper : IDataListMapper<ProductData, Product> {
    override fun tToModel(t: ProductData): Product {
        return Product(
            productID = t.productID,
            imageUrl = t.imageUrl,
            categoryID = t.categoryID,
            discontinued = t.discontinued,
            productName = t.productName,
            quantityPerUnit = t.quantityPerUnit,
            reorderLevel = t.reorderLevel,
            supplierID = t.supplierID,
            unitPrice = t.unitPrice,
            unitsInStock = t.unitsInStock,
            unitsOnOrder = t.unitsOnOrder
        )
    }

    override fun modelToT(m: Product): ProductData {
        return ProductData(
            productID = m.productID,
            imageUrl = m.imageUrl,
            categoryID = m.categoryID,
            discontinued = m.discontinued,
            productName = m.productName,
            quantityPerUnit = m.quantityPerUnit,
            reorderLevel = m.reorderLevel,
            supplierID = m.supplierID,
            unitPrice = m.unitPrice,
            unitsInStock = m.unitsInStock,
            unitsOnOrder = m.unitsOnOrder
        )
    }

    override fun tListToModel(tList: List<ProductData>): List<Product> {
        val data = arrayListOf<Product>()
        tList.forEach { productData ->
            data.add(tToModel(productData))
        }
        return data
    }

    override fun mListToT(mList: List<Product>): List<ProductData> {
        val data = arrayListOf<ProductData>()
        mList.forEach { product ->
            data.add(modelToT(product))
        }
        return data
    }
}
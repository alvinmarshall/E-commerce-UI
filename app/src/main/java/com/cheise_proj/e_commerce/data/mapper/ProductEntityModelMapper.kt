package com.cheise_proj.e_commerce.data.mapper

import com.cheise_proj.e_commerce.data.db.entity.ProductEntity
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.IDataListMapper

class ProductEntityModelMapper : IDataListMapper<Product, ProductEntity> {
    override fun tToModel(t: Product): ProductEntity {
        return ProductEntity(
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
            unitsOnOrder = t.unitsOnOrder,
            favorite = t.favorite
        )
    }

    override fun modelToT(m: ProductEntity): Product {
        return Product(
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
            unitsOnOrder = m.unitsOnOrder,
            favorite = m.favorite
        )
    }

    override fun tListToModel(tList: List<Product>): List<ProductEntity> {
        val data = arrayListOf<ProductEntity>()
        tList.forEach { product ->
            data.add(tToModel(product))
        }
        return data
    }

    override fun mListToT(mList: List<ProductEntity>): List<Product> {
        val data = arrayListOf<Product>()
        mList.forEach { entity ->
            data.add(modelToT(entity))
        }
        return data
    }
}
package com.cheise_proj.e_commerce.extension

import com.cheise_proj.e_commerce.data.db.entity.ProductEntity
import com.cheise_proj.e_commerce.data.mapper.ProductDtoModelMapper
import com.cheise_proj.e_commerce.data.mapper.ProductEntityModelMapper
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.model.dto.ProductData

fun Product.toDto() = ProductDtoModelMapper().modelToT(this)
fun ProductData.toModel() = ProductDtoModelMapper().tToModel(this)

fun List<Product>.toDtoList(): List<ProductData> = ProductDtoModelMapper().mListToT(this)
fun List<ProductData>.toModelList(): List<Product> = ProductDtoModelMapper().tListToModel(this)

fun Product.toEntity() = ProductEntityModelMapper().tToModel(this)
fun ProductEntity.toProduct() = ProductEntityModelMapper().modelToT(this)

fun List<Product>.toEntityList() = ProductEntityModelMapper().tListToModel(this)
fun List<ProductEntity>.toProductList() = ProductEntityModelMapper().mListToT(this)
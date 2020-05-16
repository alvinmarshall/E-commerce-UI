package com.cheise_proj.e_commerce.model.dto


import com.google.gson.annotations.SerializedName

data class ProductDto(
    val data: List<ProductData>
)

data class ProductData(
    @SerializedName("ProductID")
    val productID: String,
    @SerializedName("ProductName")
    val productName: String,
    @SerializedName("SupplierID")
    val supplierID: String,
    @SerializedName("CategoryID")
    val categoryID: String,
    @SerializedName("QuantityPerUnit")
    val quantityPerUnit: String,
    @SerializedName("UnitPrice")
    val unitPrice: String,
    @SerializedName("UnitsInStock")
    val unitsInStock: String,
    @SerializedName("UnitsOnOrder")
    val unitsOnOrder: String,
    @SerializedName("ReorderLevel")
    val reorderLevel: String,
    @SerializedName("Discontinued")
    val discontinued: String,
    @SerializedName("ImageUrl")
    val imageUrl: String
)
package com.cheise_proj.e_commerce.model

data class Delivery(
    val id: Int,
    val name: String,
    val duration: String,
    val imageUrl: String,
    var tracker: String?
)
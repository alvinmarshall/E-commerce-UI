package com.cheise_proj.e_commerce.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cheise_proj.e_commerce.model.Product

class ProductDiff : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.categoryID == newItem.categoryID
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
package com.cheise_proj.e_commerce.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cheise_proj.e_commerce.model.Category

class CategoryDiff : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.categoryID == newItem.categoryID
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
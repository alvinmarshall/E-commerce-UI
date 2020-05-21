package com.cheise_proj.e_commerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.CategoryDiff
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_chips.view.*

class CategoryChipAdapter :
    ListAdapter<Category, CategoryChipAdapter.CategoryChipVh>(CategoryDiff()) {
    private var itemClickListener: ItemClickListener<String?>? = null
    internal fun setItemClickCallback(callback: ItemClickListener<String?>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChipVh {
        return CategoryChipVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_chips, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryChipVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class CategoryChipVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Category?, itemClickListener: ItemClickListener<String?>?) {
            with(itemView) {
                chip_item.text = item?.categoryName
                chip_item.setOnClickListener { itemClickListener?.onClick(item?.categoryID) }
            }
        }
    }
}
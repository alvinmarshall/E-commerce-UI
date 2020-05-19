package com.cheise_proj.e_commerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.CategoryDiff
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_categories_with_image.view.*

class CategoryImageAdapter :
    ListAdapter<Category, CategoryImageAdapter.CategoryImgVh>(CategoryDiff()) {
    private var itemClickListener: ItemClickListener<Category?>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<Category?>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryImgVh {
        return CategoryImgVh(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_categories_with_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryImgVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class CategoryImgVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Category?, itemClickListener: ItemClickListener<Category?>?) {
            with(itemView) {
                tv_item_1.text = item?.categoryName
                setOnClickListener { itemClickListener?.onClick(item) }
                GlideApp.with(context).load(item?.imageUrl).centerCrop().into(img_item)
            }
        }
    }
}
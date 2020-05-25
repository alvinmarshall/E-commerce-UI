package com.cheise_proj.e_commerce.ui.review.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.Reviews
import kotlinx.android.synthetic.main.list_reviews.view.*

class ReviewAdapter :
    ListAdapter<Reviews, ReviewAdapter.ReviewVh>(ReviewDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVh {
        return ReviewVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_reviews, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewVh, position: Int) {
        holder.bind(getItem(position))
    }

    class ReviewVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Reviews?) {
            with(itemView) {
                tv_item_1.text = item?.author
                tv_item_2.text = item?.datePublished
                tv_item_3.text = item?.description
                ratingBar.rating = item?.reviewRating?.ratingValue?.toFloat() ?: 0f
                GlideApp.with(context).load(item?.avatarUrl).circleCrop().into(img_item)
            }

        }
    }
}

internal class ReviewDiff : DiffUtil.ItemCallback<Reviews>() {
    override fun areItemsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Reviews, newItem: Reviews): Boolean {
        return oldItem == newItem
    }
}
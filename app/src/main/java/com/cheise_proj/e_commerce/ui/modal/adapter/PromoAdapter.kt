package com.cheise_proj.e_commerce.ui.modal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.PromoCode
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_promo_codes.view.*

class PromoAdapter :
    ListAdapter<PromoCode, PromoAdapter.PromoVh>(PromoDiff()) {
    private var itemClickListener: ItemClickListener<String?>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<String?>) {
        itemClickListener = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoVh {
        return PromoVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_promo_codes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PromoVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class PromoVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PromoCode?, itemClickListener: ItemClickListener<String?>?) {
            with(itemView) {
                tv_item_1.text = item?.elapsedTime
                tv_item_2.text = item?.name
                tv_item_3.text = item?.title
                btn_apply.setOnClickListener {
                    itemClickListener?.onClick(item?.title)
                }
                GlideApp.with(context).load(item?.imageUrl).centerCrop().into(img_item)
            }
        }
    }
}

internal class PromoDiff : DiffUtil.ItemCallback<PromoCode>() {
    override fun areItemsTheSame(oldItem: PromoCode, newItem: PromoCode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PromoCode, newItem: PromoCode): Boolean {
        return oldItem == newItem
    }
}
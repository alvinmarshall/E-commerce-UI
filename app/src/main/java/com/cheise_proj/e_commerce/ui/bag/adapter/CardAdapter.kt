package com.cheise_proj.e_commerce.ui.bag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.extension.hideCardDigit
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_payment_card.view.*

class CardAdapter :
    ListAdapter<CardEntity, CardAdapter.CardVh>(CardDiff()) {
    companion object {
        const val CARD_MASTER = "master"
        const val CARD_VISA = "visa"
    }

    private var itemClickListener: ItemClickListener<CardEntity>? = null


    internal fun setItemClickCallback(callback: ItemClickListener<CardEntity>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVh {
        return CardVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_payment_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class CardVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: CardEntity?,
            itemClickListener: ItemClickListener<CardEntity>?
        ) {
            with(itemView) {
                tv_item_1.text = item?.cardNumber?.hideCardDigit()
                tv_item_2.text = item?.nameOnCard
                tv_item_3.text = item?.expiredDate
                cb_default.isChecked = item?.isDefault!! > 0
                cb_default.setOnCheckedChangeListener { _, isChecked ->
                    applyDefaultCard(isChecked, item, itemClickListener)
                }
                if (item.type.contains(CARD_MASTER)) {
                    GlideApp.with(context).load(context.getDrawable(R.drawable.master_card))
                        .centerCrop().into(img_item)

                } else {

                    GlideApp.with(context).load(context.getDrawable(R.drawable.visa_card))
                        .centerCrop().into(img_item)
                }
            }
        }


        private fun applyDefaultCard(
            isChecked: Boolean,
            item: CardEntity,
            itemClickListener: ItemClickListener<CardEntity>?
        ) {
            if (isChecked) {
                if (item.isDefault > 0) return
                item.isDefault = 1
                itemClickListener?.onClick(item)
            } else {
                if (item.isDefault == 0) return
                item.isDefault = 0
                itemClickListener?.onClick(item)
            }
        }

    }
}

internal class CardDiff : DiffUtil.ItemCallback<CardEntity>() {
    override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem == newItem
    }
}
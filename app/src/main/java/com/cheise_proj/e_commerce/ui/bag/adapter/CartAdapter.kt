package com.cheise_proj.e_commerce.ui.bag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.utils.CartOptions
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_cart.view.*

class CartAdapter :
    ListAdapter<ProductWithCart, CartAdapter.CartVh>(
        ProductCartDiff()
    ) {
    private var itemClickListener: ItemClickListener<Pair<CartOptions, ProductWithCart?>>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<Pair<CartOptions, ProductWithCart?>>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartVh {
        return CartVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class CartVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: ProductWithCart?,
            itemClickListener: ItemClickListener<Pair<CartOptions, ProductWithCart?>>?
        ) {
            with(itemView) {
                tv_item_1.text = item?.productEntity?.productName
                tv_item_2.text =
                    context.getString(R.string.color_placeholder, item?.cartEntity?.color)
                tv_item_3.text =
                    context.getString(R.string.size_placeholder, item?.cartEntity?.size)
                tv_item_4.text = context.getString(
                    R.string.money_end_placeholder,
                    item?.productEntity?.unitPrice
                )
                tv_item_count.text = "${item?.cartEntity?.quantity ?: 0}"
                GlideApp.with(context).load(item?.productEntity?.imageUrl).centerCrop()
                    .into(img_item)

                fab_add.setOnClickListener {
                    itemClickListener?.onClick(Pair(CartOptions.INCREASE, item))
                }

                fab_remove.setOnClickListener {
                    itemClickListener?.onClick(Pair(CartOptions.DECREASE, item))
                }
                btn_menu.setOnClickListener {
                    applyPopMenu(itemClickListener, item)
                }
            }
        }

        private fun View.applyPopMenu(
            itemClickListener: ItemClickListener<Pair<CartOptions, ProductWithCart?>>?,
            item: ProductWithCart?
        ) {
            val popup = PopupMenu(context, btn_menu)
            popup.inflate(R.menu.cart_option)
            popup.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.action_favorite -> {
                        itemClickListener?.onClick(Pair(CartOptions.FAVORITE, item))
                        true
                    }
                    R.id.action_remove -> {
                        itemClickListener?.onClick(Pair(CartOptions.REMOVE, item))
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}

internal class ProductCartDiff : DiffUtil.ItemCallback<ProductWithCart>() {
    override fun areItemsTheSame(oldItem: ProductWithCart, newItem: ProductWithCart): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductWithCart, newItem: ProductWithCart): Boolean {
        return oldItem == newItem
    }
}
package com.cheise_proj.e_commerce.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.utils.FavoriteOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_favorite_grid.view.*


class FavoriteGridAdapter :
    ListAdapter<ProductWithFavorite, FavoriteGridAdapter.FavoriteGridVh>(FavoriteDiff()) {
    private var itemClickListener: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>? =
        null

    internal fun setClickItemCallback(callback: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGridVh {
        return FavoriteGridVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_favorite_grid, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteGridVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class FavoriteGridVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rating = kotlin.math.floor(Math.random() * 5).toInt()
        private val discount = kotlin.math.floor(Math.random() * 40).toInt()

        fun bind(
            item: ProductWithFavorite?,
            itemClickListener: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>?
        ) {
            with(itemView) {
                tv_discount_banner.text =
                    context.getString(R.string.percentage_text_placeholder, -discount, "%")
                tv_item_1.text = item?.productEntity?.productName
                tv_item_2.text =
                    context.getString(R.string.color_placeholder, item?.favoriteEntity?.color)
                tv_item_3.text =
                    context.getString(R.string.size_placeholder, item?.favoriteEntity?.size)
                tv_item_4.text = context.getString(
                    R.string.money_end_placeholder,
                    item?.productEntity?.unitPrice
                )
                ratingBar.rating = rating.toFloat()
                btn_close.setOnClickListener {
                    itemClickListener?.onClick(
                        Pair(
                            FavoriteOption.CLOSE,
                            item
                        )
                    )
                }
                fab_cart.setOnClickListener {
                    itemClickListener?.onClick(
                        Pair(
                            FavoriteOption.CART,
                            item
                        )
                    )
                }
                GlideApp.with(context).load(item?.productEntity?.imageUrl).centerCrop()
                    .into(img_item)

            }
        }
    }
}
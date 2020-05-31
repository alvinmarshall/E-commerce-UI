package com.cheise_proj.e_commerce.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.utils.FavoriteOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_favorite.view.*

class FavoriteAdapter :
    ListAdapter<ProductWithFavorite, FavoriteVh>(FavoriteDiff()) {

    private var itemClickListener: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>? =
        null

    internal fun setClickItemCallback(callback: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVh {
        return FavoriteVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_favorite, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

}

class FavoriteVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val rating = kotlin.math.floor(Math.random() * 5).toInt()
    fun bind(
        item: ProductWithFavorite?,
        itemClickListener: ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>>?
    ) {
        with(itemView) {
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

internal class FavoriteDiff : DiffUtil.ItemCallback<ProductWithFavorite>() {
    override fun areItemsTheSame(
        oldItem: ProductWithFavorite,
        newItem: ProductWithFavorite
    ): Boolean {
        return oldItem.favoriteEntity == newItem.favoriteEntity
    }

    override fun areContentsTheSame(
        oldItem: ProductWithFavorite,
        newItem: ProductWithFavorite
    ): Boolean {
        return oldItem.favoriteEntity == newItem.favoriteEntity
    }
}
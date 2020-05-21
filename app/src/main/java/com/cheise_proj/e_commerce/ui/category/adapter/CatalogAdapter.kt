package com.cheise_proj.e_commerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.ProductDiff
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.utils.CatalogOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_catalog.view.*

class CatalogAdapter :
    ListAdapter<Product, CatalogAdapter.CatalogVh>(ProductDiff()) {
    private var itemClickListener: ItemClickListener<Pair<Product?, CatalogOption>>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<Pair<Product?, CatalogOption>>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogVh {
        return CatalogVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_catalog, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatalogVh, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class CatalogVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: Product?,
            itemClickListener: ItemClickListener<Pair<Product?, CatalogOption>>?
        ) {
            with(itemView) {
                tv_item_1.text = item?.productName
                tv_item_2.text = item?.productName
                tv_item_3.text = item?.unitPrice
                setOnClickListener { itemClickListener?.onClick(Pair(item, CatalogOption.VIEW)) }
                GlideApp.with(context).load(item?.imageUrl).centerCrop().into(img_item)
                applyFavorite(itemClickListener, item)
            }
        }

        private fun View.applyFavorite(
            itemClickListener: ItemClickListener<Pair<Product?, CatalogOption>>?,
            item: Product?
        ) {
            fab_fav.setOnClickListener {
                fab_fav.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_favorite
                    )
                )
                itemClickListener?.onClick(Pair(item, CatalogOption.FAVORITE))

            }
        }


    }
}

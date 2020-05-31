package com.cheise_proj.e_commerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.utils.ClickOption
import com.cheise_proj.e_commerce.utils.HorizontalAdapterOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_vertical_product.view.*

class VerticalProductAdapter :
    ListAdapter<Category, VerticalProductAdapter.ProductCategoryVH>(CategoryDiff()) {
    private var itemClickListener: ItemClickListener<Pair<Category?, ClickOption>>? = null
    private var horizontalItemClickListener: ItemClickListener<Pair<HorizontalAdapterOption, String?>>? =
        null


    internal fun setItemClickCallback(callback: ItemClickListener<Pair<Category?, ClickOption>>) {
        itemClickListener = callback
    }

    internal fun setHorizontalItemClickCallback(callback: ItemClickListener<Pair<HorizontalAdapterOption, String?>>) {
        horizontalItemClickListener = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryVH {
        return ProductCategoryVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_vertical_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductCategoryVH, position: Int) {
        holder.bind(getItem(position), itemClickListener, horizontalItemClickListener)
    }

    class ProductCategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val horizontalAdapter: HorizontalProductAdapter = HorizontalProductAdapter()

        fun bind(
            item: Category?,
            itemClickListener: ItemClickListener<Pair<Category?, ClickOption>>?,
            horizontalItemClickListener: ItemClickListener<Pair<HorizontalAdapterOption, String?>>?
        ) {
            applyAdapter(item, horizontalItemClickListener)

            with(itemView) {
                tv_header.text = item?.categoryName
                tv_sub_header.text = item?.description
                initRecyclerView()
                this.setOnClickListener { itemClickListener?.onClick(Pair(item, ClickOption.VIEW)) }
                btn_view_all.setOnClickListener {
                    itemClickListener?.onClick(
                        Pair(
                            item,
                            ClickOption.VIEW_ALL
                        )
                    )
                }
            }


        }

        private fun applyAdapter(
            item: Category?,
            horizontalItemClickListener: ItemClickListener<Pair<HorizontalAdapterOption, String?>>?
        ) {
            horizontalAdapter.submitList(item?.product)
            horizontalAdapter.setItemCallback(horizontalItemClickListener)
        }

        private fun View.initRecyclerView() {
            recycler_view.apply {
                hasFixedSize()
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = horizontalAdapter
            }
        }

    }
}


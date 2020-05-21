package com.cheise_proj.e_commerce.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.FilterOpts
import com.cheise_proj.e_commerce.utils.*
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.brand_opt_item.view.*
import kotlinx.android.synthetic.main.chip_items.view.*
import kotlinx.android.synthetic.main.color_palette.view.*
import kotlinx.android.synthetic.main.color_palette.view.tv_item_1

class FilterAdapter :
    ListAdapter<FilterOpts, RecyclerView.ViewHolder>(FilterDiff()) {
    private var colorData: Array<Int> = emptyArray()
    private var sizesData: Array<String> = emptyArray()
    private var brandOptsData: Array<String> = emptyArray()
    private var categoryOptsData: Array<String> = emptyArray()
    private var brandItemClickListener: ItemClickListener<String?>? = null

    internal fun addSizesOpts(
        sizeList: Array<String>
    ) {
        sizesData = sizeList

    }

    internal fun addColorOpts(colorList: Array<Int>) {
        colorData = colorList
    }

    internal fun addCategoryOpts(categoryOpts: Array<String>) {
        categoryOptsData = categoryOpts
    }

    internal fun addBrandOpts(brandOpts: Array<String>) {
        brandOptsData = brandOpts
    }

    internal fun brandClickCallback(callback: ItemClickListener<String?>) {
        brandItemClickListener = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            COLORS_VIEW -> ColorsVH(
                LayoutInflater.from(parent.context).inflate(R.layout.color_palette, parent, false)
            )
            SIZE_VIEW -> SizesVH(
                LayoutInflater.from(parent.context).inflate(R.layout.chip_items, parent, false)
            )
            CATEGORY_VIEW -> CategoryOptsVH(
                LayoutInflater.from(parent.context).inflate(R.layout.chip_items, parent, false)
            )
            BRAND_VIEW -> BrandOptsVH(
                LayoutInflater.from(parent.context).inflate(R.layout.brand_opt_item, parent, false)
            )
            else -> PriceRangeVH(
                LayoutInflater.from(parent.context).inflate(R.layout.price_slider, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).viewType == PRICE_VIEW) return PRICE_VIEW
        if (getItem(position).viewType == COLORS_VIEW) return COLORS_VIEW
        if (getItem(position).viewType == SIZE_VIEW) return SIZE_VIEW
        if (getItem(position).viewType == CATEGORY_VIEW) return CATEGORY_VIEW
        if (getItem(position).viewType == BRAND_VIEW) return BRAND_VIEW
        return super.getItemViewType(position)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == PRICE_VIEW) {
            (holder as PriceRangeVH).bind(getItem(position))
        }
        if (getItemViewType(position) == COLORS_VIEW) {
            (holder as ColorsVH).bind(getItem(position), colorData)
        }
        if (getItemViewType(position) == SIZE_VIEW) {
            (holder as SizesVH).bind(getItem(position), sizesData)
        }
        if (getItemViewType(position) == CATEGORY_VIEW) {
            (holder as CategoryOptsVH).bind(getItem(position), categoryOptsData)
        }
        if (getItemViewType(position) == BRAND_VIEW) {
            (holder as BrandOptsVH).bind(getItem(position), brandOptsData, brandItemClickListener)
        }
    }
}


internal class PriceRangeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?
    ) {
        with(itemView) {
            tv_item_1.text = item?.title
        }
    }
}

internal class ColorsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        colorData: Array<Int>
    ) {

        with(itemView) {
            tv_item_1.text = item?.title

            applyColorPalette(colorData)
        }

    }

    private fun View.applyColorPalette(colorData: Array<Int>) {
        for (i in colorData.indices) {
            val imageView = LayoutInflater.from(context)
                .inflate(R.layout.circular_img, color_group, false) as ImageView
            imageView.setPadding(5, 0, 5, 0)
            val colorDrawable = ColorDrawable(colorData[i])
            GlideApp.with(context).load(colorDrawable).circleCrop().into(imageView)
            color_group.addView(imageView)
        }
    }
}

internal class SizesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        sizesData: Array<String>
    ) {
        with(itemView) {
            tv_item_1.text = item?.title
            applySizesChips(sizesData)

        }
    }

    private fun View.applySizesChips(
        sizesData: Array<String>
    ) {
        for (i in sizesData.indices) {
            val chip = LayoutInflater.from(context)
                .inflate(R.layout.chip_single, chip_group, false) as Chip
            chip.text = sizesData[i]
            chip_group.addView(chip)
        }
    }
}

internal class CategoryOptsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        categoryOptsData: Array<String>
    ) {
        with(itemView) {
            tv_item_1.text = item?.title

            applyCategoryChips(categoryOptsData)

        }

    }

    private fun View.applyCategoryChips(
        categoryOptsData: Array<String>
    ) {
        for (i in categoryOptsData.indices) {
            val chip = LayoutInflater.from(context)
                .inflate(R.layout.chip_single, chip_group, false) as Chip
            chip.text = categoryOptsData[i]
            chip_group.addView(chip)
        }
    }
}

internal class BrandOptsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        brandOptsData: Array<String>,
        brandItemClickListener: ItemClickListener<String?>?
    ) {
        val sb = StringBuilder()
        brandOptsData.forEach { brand ->
            sb.append("$brand,")
        }
        with(itemView) {
            tv_item_1.text = item?.title
            tv_item_2.text = sb
            btn_next
                .setOnClickListener { brandItemClickListener?.onClick(item?.title) }
        }
    }
}

class FilterDiff : DiffUtil.ItemCallback<FilterOpts>() {
    override fun areItemsTheSame(oldItem: FilterOpts, newItem: FilterOpts): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FilterOpts, newItem: FilterOpts): Boolean {
        return oldItem == newItem
    }
}
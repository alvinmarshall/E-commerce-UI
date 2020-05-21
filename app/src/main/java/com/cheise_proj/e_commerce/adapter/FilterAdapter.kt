package com.cheise_proj.e_commerce.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.BrandOpts
import com.cheise_proj.e_commerce.model.CategoryOpts
import com.cheise_proj.e_commerce.model.FilterOpts
import com.cheise_proj.e_commerce.model.PriceRange
import com.cheise_proj.e_commerce.utils.*
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.brand_opt_item.view.*
import kotlinx.android.synthetic.main.chip_items.view.*
import kotlinx.android.synthetic.main.color_palette.view.tv_item_1

class FilterAdapter :
    ListAdapter<FilterOpts, RecyclerView.ViewHolder>(FilterDiff()) {
    private var colorData: List<Int> = emptyList()
    private var sizesData: List<String> = emptyList()
    private var priceRangeData: List<PriceRange> = emptyList()
    private var brandOptsData: List<BrandOpts> = emptyList()
    private var categoryOptsData: List<CategoryOpts> = emptyList()

    internal fun addSizesOpts(
        sizeList: List<String>
    ) {
        sizesData = sizeList

    }

    internal fun addColorOpts(colorList: List<Int>) {
        colorData = colorList
    }

    internal fun addCategoryOpts(categoryOpts: List<CategoryOpts>) {
        categoryOptsData = categoryOpts
    }

    internal fun addBrandOpts(brandOpts: List<BrandOpts>) {
        brandOptsData = brandOpts
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
            (holder as BrandOptsVH).bind(getItem(position), brandOptsData)
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
        colorData: List<Int>?
    ) {

        with(itemView) {
            tv_item_1.text = item?.title
        }

    }
}

internal class SizesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        sizesData: List<String>
    ) {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(5, 0, 5, 0)


        with(itemView) {
            tv_item_1.text = item?.title
            applySizesChips(sizesData, params)
        }
    }

    private fun View.applySizesChips(
        sizesData: List<String>,
        params: LinearLayout.LayoutParams
    ) {
        for (i in sizesData.indices) {
            val chip = Chip(context)
            chip.gravity = Gravity.CENTER
            chip.layoutParams = params
            chip.text = sizesData[i]
            chip_group.addView(chip)
        }
    }
}

internal class CategoryOptsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        categoryOptsData: List<CategoryOpts>
    ) {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(5, 0, 5, 0)


        with(itemView) {
            tv_item_1.text = item?.title

            applyCategoryChips(categoryOptsData, params)

        }

    }

    private fun View.applyCategoryChips(
        categoryOptsData: List<CategoryOpts>,
        params: LinearLayout.LayoutParams
    ) {
        for (i in categoryOptsData.indices) {
            val chip = Chip(context)
            chip.gravity = Gravity.CENTER
            chip.layoutParams = params
            chip.text = categoryOptsData[i].title
            chip_group.addView(chip)
        }
    }
}

internal class BrandOptsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: FilterOpts?,
        brandOptsData: List<BrandOpts>
    ) {
        val sb = StringBuilder()
        brandOptsData.forEach { brand ->
            sb.append("${brand.title},")
        }
        with(itemView) {
            tv_item_1.text = item?.title
            tv_item_2.text = sb
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
package com.cheise_proj.e_commerce.ui.bag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.list_address.view.*

class AddressAdapter :
    ListAdapter<AddressEntity, AddressAdapter.AddressVh>(AddressDiff()) {
    private var itemClickListener: ItemClickListener<AddressEntity>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<AddressEntity>) {
        itemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressVh {
        return AddressVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_address, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AddressVh, position: Int) {
        holder.bind(getItem(position), position, itemClickListener)
    }

    class AddressVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var selectedItem = -1
        fun bind(
            item: AddressEntity?,
            position: Int,
            itemClickListener: ItemClickListener<AddressEntity>?
        ) {
            with(itemView) {
                tv_item_1.text = item?.fullName
                tv_item_2.text = item?.address
                tv_item_3.text = context.getString(
                    R.string.full_address_placeholder,
                    item?.city,
                    item?.zipCode,
                    item?.country
                )
                cb_default.isChecked = selectedItem == position || item?.isDefault!! > 0

                cb_default.setOnCheckedChangeListener { _, isChecked ->
                    selectedItem = position
                    if (isChecked) {
                        item?.isDefault = 1
                        itemClickListener?.onClick(item!!)
                    }
                }
            }
        }
    }
}

internal class AddressDiff : DiffUtil.ItemCallback<AddressEntity>() {
    override fun areItemsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean {
        return oldItem == newItem
    }
}
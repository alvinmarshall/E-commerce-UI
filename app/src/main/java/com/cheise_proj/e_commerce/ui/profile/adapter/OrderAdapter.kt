package com.cheise_proj.e_commerce.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.Orders
import com.cheise_proj.e_commerce.extension.toSimpleDate
import kotlinx.android.synthetic.main.list_orders.view.*

class OrderAdapter :
    ListAdapter<Orders, OrderAdapter.OrderVh>(OrdersDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderVh {
        return OrderVh(
            LayoutInflater.from(parent.context).inflate(R.layout.list_orders, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderVh, position: Int) {
        holder.bind(getItem(position))
    }

    class OrderVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Orders?) {
            with(itemView) {
                tv_item_1.text =
                    context.getString(R.string.order_number_placeholder, item?.orderEntity?.id)
                tv_item_2.text = item?.orderEntity?.date?.toSimpleDate()
                tv_item_3.text = item?.orderEntity?.tracker
                tv_item_4.text = "${item?.orderEntity?.quantity}"
                tv_item_5.text =
                    context.getString(R.string.money_end_placeholder, item?.orderEntity?.total)
                tv_item_6.text = if (item?.orderEntity?.status == 1) "Delivered" else "Processing"
            }

        }
    }
}

internal class OrdersDiff : DiffUtil.ItemCallback<Orders>() {
    override fun areItemsTheSame(oldItem: Orders, newItem: Orders): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Orders, newItem: Orders): Boolean {
        return oldItem == newItem
    }
}
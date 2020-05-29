package com.cheise_proj.e_commerce.ui.bag.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.extension.hideCardDigit
import com.cheise_proj.e_commerce.model.Checkout
import com.cheise_proj.e_commerce.utils.CheckoutOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.checkout_address.view.*
import kotlinx.android.synthetic.main.checkout_address.view.btn_change
import kotlinx.android.synthetic.main.checkout_address.view.tv_header
import kotlinx.android.synthetic.main.checkout_address.view.tv_item_1
import kotlinx.android.synthetic.main.checkout_payment.view.*

class CheckoutAdapter :
    ListAdapter<Checkout, RecyclerView.ViewHolder>(CheckoutDiff()) {
    companion object {
        const val SHIPPING_VIEW = 0
        const val PAYMENT_VIEW = 1
        const val DELIVERY_VIEW = 2
    }

    private var _address: AddressEntity? = null
    private var _card: CardEntity? = null
    private var itemClickListener: ItemClickListener<CheckoutOption>? = null

    internal fun addShippingAddress(address: AddressEntity?) {
        _address = address
    }


    internal fun addPaymentMethod(card: CardEntity?) {
        _card = card
    }

    internal fun addDeliveryMethod() {}
    internal fun setItemClickCallback(callback: ItemClickListener<CheckoutOption>) {
        itemClickListener = callback
    }


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            PAYMENT_VIEW -> PAYMENT_VIEW
            DELIVERY_VIEW -> DELIVERY_VIEW
            else -> SHIPPING_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PAYMENT_VIEW -> PaymentVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.checkout_payment, parent, false)
            )
            DELIVERY_VIEW -> DeliveryMethodVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.checkout_delivery, parent, false)
            )
            else -> ShippingAddressVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.checkout_address, parent, false)
            )

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == SHIPPING_VIEW) {
            (holder as ShippingAddressVh).bind(getItem(position), _address, itemClickListener)

        }
        if (getItemViewType(position) == PAYMENT_VIEW) {
            (holder as PaymentVh).bind(getItem(position), _card, itemClickListener)

        }
        if (getItemViewType(position) == DELIVERY_VIEW) {
            (holder as DeliveryMethodVh).bind(getItem(position))

        }
    }


}

internal class ShippingAddressVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Checkout?,
        _address: AddressEntity?,
        itemClickListener: ItemClickListener<CheckoutOption>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_item_1.text = _address?.fullName ?: "Not Set"
            tv_item_2.text = _address?.address ?: "Not set"
            tv_item_3.text = context.getString(
                R.string.full_address_placeholder,
                _address?.city,
                _address?.zipCode,
                _address?.country
            )
            btn_change.setOnClickListener { itemClickListener?.onClick(CheckoutOption.ADDRESS_CHANGE) }
        }
    }
}

internal class PaymentVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Checkout?,
        _card: CardEntity?,
        itemClickListener: ItemClickListener<CheckoutOption>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_item_1.text = _card?.cardNumber?.hideCardDigit() ?: "Not set"
            btn_change.setOnClickListener { itemClickListener?.onClick(CheckoutOption.PAYMENT_CHANGE) }
            GlideApp.with(context).load(_card?.imageUrl).into(img_item)
        }
    }
}

internal class DeliveryMethodVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Checkout?) {
        with(itemView) {
            tv_header.text = item?.title

        }

    }
}

internal class CheckoutDiff : DiffUtil.ItemCallback<Checkout>() {
    override fun areItemsTheSame(oldItem: Checkout, newItem: Checkout): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Checkout, newItem: Checkout): Boolean {
        return oldItem == newItem
    }
}
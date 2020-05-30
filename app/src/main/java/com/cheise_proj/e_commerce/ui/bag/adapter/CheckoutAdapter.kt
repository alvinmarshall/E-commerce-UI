package com.cheise_proj.e_commerce.ui.bag.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.extension.hideCardDigit
import com.cheise_proj.e_commerce.model.Checkout
import com.cheise_proj.e_commerce.utils.CheckoutOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.checkout_address.view.*
import kotlinx.android.synthetic.main.checkout_address.view.btn_change
import kotlinx.android.synthetic.main.checkout_address.view.tv_header
import kotlinx.android.synthetic.main.checkout_address.view.tv_item_1
import kotlinx.android.synthetic.main.checkout_delivery.view.*
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
    private var _deliveryList: List<DeliveryEntity>? = emptyList()
    private var itemClickListener: ItemClickListener<Pair<CheckoutOption,Int?>>? = null

    internal fun addShippingAddress(address: AddressEntity?) {
        _address = address
    }


    internal fun addPaymentMethod(card: CardEntity?) {
        _card = card
    }

    internal fun addDeliveryMethod(deliveryList: List<DeliveryEntity>) {
        _deliveryList = deliveryList
    }

    internal fun setItemClickCallback(callback: ItemClickListener<Pair<CheckoutOption,Int?>>) {
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
            (holder as DeliveryMethodVh).bind(getItem(position), _deliveryList,itemClickListener)

        }
    }


}

internal class ShippingAddressVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Checkout?,
        _address: AddressEntity?,
        itemClickListener: ItemClickListener<Pair<CheckoutOption,Int?>>?
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
            btn_change.setOnClickListener { itemClickListener?.onClick(Pair(CheckoutOption.ADDRESS_CHANGE,_address?.id)) }
        }
    }
}

internal class PaymentVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Checkout?,
        _card: CardEntity?,
        itemClickListener: ItemClickListener<Pair<CheckoutOption,Int?>>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_item_1.text = _card?.cardNumber?.hideCardDigit() ?: "Not set"
            btn_change.setOnClickListener { itemClickListener?.onClick(Pair(CheckoutOption.PAYMENT_CHANGE,_card?.id)) }
            GlideApp.with(context).load(_card?.imageUrl).into(img_item)
        }
    }
}

internal class DeliveryMethodVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: Checkout?,
        _deliveryList: List<DeliveryEntity>?,
        itemClickListener: ItemClickListener<Pair<CheckoutOption,Int?>>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            img_container.removeAllViews()
            _deliveryList?.forEach { delivery ->
                val cardView =
                    LayoutInflater.from(context).inflate(R.layout.card_image, img_container, false)
                cardView.setOnClickListener { itemClickListener?.onClick(Pair(CheckoutOption.DELIVERY,delivery.id)) }
                val imageView = cardView.findViewById<ImageView>(R.id.img_item)
                val subText = cardView.findViewById<TextView>(R.id.tv_sub_header)
                GlideApp.with(context)
                    .load(delivery.imageUrl)
                    .centerCrop().into(imageView)
                subText.text = delivery.duration
                img_container.addView(cardView)
            }

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
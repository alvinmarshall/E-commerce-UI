package com.cheise_proj.e_commerce.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.model.ProfileSection
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.profile_title_section.view.*

class ProfileAdapter :
    ListAdapter<ProfileSection, RecyclerView.ViewHolder>(ProfileSectionDiff()) {
    companion object {
        const val ORDERS_VIEW = 0
        const val ADDRESS_VIEW = 1
        const val PAYMENT_VIEW = 2
        const val PROMO_CODE_VIEW = 3
        const val REVIEW_VIEW = 4
        const val SETTINGS_VIEW = 5

        enum class ProfileOption {
            ORDER_DETAILS, ADDRESS, PAYMENT_METHOD
        }

    }

    private var itemClickListener: ItemClickListener<ProfileOption>? = null

    internal fun setItemClickCallback(callback: ItemClickListener<ProfileOption>) {
        itemClickListener = callback
    }

    private var _shippingAddress: Int? = 0
    private var _orderCount: Int? = 0
    private var _paymentMethod: CardEntity? = null
    internal fun addShippingAddressCount(shippingAddress: Int) {
        _shippingAddress = shippingAddress
    }

    internal fun addOrderCount(order: Int?) {
        _orderCount = order
    }

    internal fun addPaymentMethod(cardEntity: CardEntity?) {
        _paymentMethod = cardEntity

    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            ADDRESS_VIEW -> ADDRESS_VIEW
            PAYMENT_VIEW -> PAYMENT_VIEW
            PROMO_CODE_VIEW -> PROMO_CODE_VIEW
            REVIEW_VIEW -> REVIEW_VIEW
            SETTINGS_VIEW -> SETTINGS_VIEW
            else -> ORDERS_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            ADDRESS_VIEW -> ShippingAddressVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_title_section, parent, false)
            )

            PAYMENT_VIEW -> PaymentMethodVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_title_section, parent, false)
            )
            PROMO_CODE_VIEW -> PromoCodeVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_title_section, parent, false)
            )
            REVIEW_VIEW -> MyReviewsVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_title_section, parent, false)
            )
            SETTINGS_VIEW -> SettingsVh(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_title_section, parent, false)
            )
            else ->
                OrdersVh(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.profile_title_section, parent, false)
                )

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ORDERS_VIEW) {
            (holder as OrdersVh).bind(getItem(position), _orderCount, itemClickListener)
        }
        if (getItemViewType(position) == ADDRESS_VIEW) {
            (holder as ShippingAddressVh).bind(
                getItem(position), _shippingAddress,
                itemClickListener
            )
        }
        if (getItemViewType(position) == PAYMENT_VIEW) {
            (holder as PaymentMethodVh).bind(getItem(position), _paymentMethod, itemClickListener)
        }
        if (getItemViewType(position) == PROMO_CODE_VIEW) {
            (holder as PromoCodeVh).bind(getItem(position))
        }
        if (getItemViewType(position) == REVIEW_VIEW) {
            (holder as MyReviewsVh).bind(getItem(position))
        }
        if (getItemViewType(position) == SETTINGS_VIEW) {
            (holder as SettingsVh).bind(getItem(position))
        }
    }

}

internal class OrdersVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: ProfileSection?,
        _orderCount: Int?,
        _itemClickListener: ItemClickListener<ProfileAdapter.Companion.ProfileOption>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_sub_header.text = "Already have $_orderCount order(s)"
            btn_detail.setOnClickListener { _itemClickListener?.onClick(ProfileAdapter.Companion.ProfileOption.ORDER_DETAILS) }
        }
    }
}

internal class ShippingAddressVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: ProfileSection?,
        _shippingAddressCount: Int?,
        _itemClickListener: ItemClickListener<ProfileAdapter.Companion.ProfileOption>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_sub_header.text = "${_shippingAddressCount ?: 0} Address"
            btn_detail.setOnClickListener { _itemClickListener?.onClick(ProfileAdapter.Companion.ProfileOption.ADDRESS) }
        }
    }
}

internal class PaymentMethodVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        item: ProfileSection?,
        _paymentMethod: CardEntity?,
        _itemClickListener: ItemClickListener<ProfileAdapter.Companion.ProfileOption>?
    ) {
        with(itemView) {
            tv_header.text = item?.title
            tv_sub_header.text =
                "${_paymentMethod?.type} ${_paymentMethod?.cardNumber?.replaceRange(
                    IntRange(0, 11),
                    "**"
                )}"
            btn_detail.setOnClickListener { _itemClickListener?.onClick(ProfileAdapter.Companion.ProfileOption.PAYMENT_METHOD) }

        }
    }
}

internal class PromoCodeVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ProfileSection?) {
        with(itemView) {
            tv_header.text = item?.title
        }
    }
}

internal class MyReviewsVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ProfileSection?) {
        with(itemView) {
            tv_header.text = item?.title
        }
    }
}

internal class SettingsVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ProfileSection?) {
        with(itemView) {
            tv_sub_header.text = "Notifications, password"
            tv_header.text = item?.title
        }
    }
}

internal class ProfileSectionDiff : DiffUtil.ItemCallback<ProfileSection>() {
    override fun areItemsTheSame(oldItem: ProfileSection, newItem: ProfileSection): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ProfileSection, newItem: ProfileSection): Boolean {
        return oldItem == newItem
    }
}
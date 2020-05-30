package com.cheise_proj.e_commerce.model

import android.os.Parcel
import android.os.Parcelable

data class CartExtras(
    val totalAmount: Float?,
    val promoCode: String?,
    val quantity:Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(totalAmount)
        parcel.writeString(promoCode)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartExtras> {
        override fun createFromParcel(parcel: Parcel): CartExtras {
            return CartExtras(parcel)
        }

        override fun newArray(size: Int): Array<CartExtras?> {
            return arrayOfNulls(size)
        }
    }
}
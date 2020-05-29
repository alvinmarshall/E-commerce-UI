package com.cheise_proj.e_commerce.ui.bag.module

import com.cheise_proj.e_commerce.ui.bag.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BagModule {
    @ContributesAndroidInjector
    fun contributeCartFragment(): CartFragment

    @ContributesAndroidInjector
    fun contributeCheckoutFragment(): CheckoutFragment

    @ContributesAndroidInjector
    fun contributeAddressFragment(): AddressFragment

    @ContributesAndroidInjector
    fun contributeCreateAddressFragment(): CreateAddressFragment

    @ContributesAndroidInjector
    fun contributePaymentCardFragment(): PaymentCardFragment
}
package com.cheise_proj.e_commerce.ui.product.module

import com.cheise_proj.e_commerce.ui.product.Product2Fragment
import com.cheise_proj.e_commerce.ui.product.ProductDetailFragment
import com.cheise_proj.e_commerce.ui.product.ProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ProductModule {
    @ContributesAndroidInjector
    fun contributeProductFragment(): ProductFragment

    @ContributesAndroidInjector
    fun contributeProduct2Fragment(): Product2Fragment

    @ContributesAndroidInjector
    fun contributeProductDetail(): ProductDetailFragment
}
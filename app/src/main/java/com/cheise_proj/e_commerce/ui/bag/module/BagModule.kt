package com.cheise_proj.e_commerce.ui.bag.module

import com.cheise_proj.e_commerce.ui.bag.CartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BagModule {
    @ContributesAndroidInjector
    fun contributeCartFragment(): CartFragment
}
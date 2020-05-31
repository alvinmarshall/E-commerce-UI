package com.cheise_proj.e_commerce.ui.profile.module

import com.cheise_proj.e_commerce.ui.profile.OrdersFragment
import com.cheise_proj.e_commerce.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ProfileModule {
    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun contributeOrdersFragment(): OrdersFragment
}
package com.cheise_proj.e_commerce.ui.category.module

import com.cheise_proj.e_commerce.ui.category.ui.Category2Fragment
import com.cheise_proj.e_commerce.ui.category.ui.Category3Fragment
import com.cheise_proj.e_commerce.ui.category.ui.CategoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CategoryModule {
    @ContributesAndroidInjector
    fun contributeCategoryFragment(): CategoryFragment
    @ContributesAndroidInjector
    fun contributeCategory2Fragment(): Category2Fragment
    @ContributesAndroidInjector
    fun contributeCategory3Fragment(): Category3Fragment
}
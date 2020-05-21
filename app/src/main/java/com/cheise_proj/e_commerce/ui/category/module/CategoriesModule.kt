package com.cheise_proj.e_commerce.ui.category.module

import com.cheise_proj.e_commerce.ui.category.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CategoriesModule {
    @ContributesAndroidInjector
    fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    fun contributeCategory2Fragment(): Category2Fragment

    @ContributesAndroidInjector
    fun contributeCategory3Fragment(): Category3Fragment

}

@Module
interface CategoryModule {
    @ContributesAndroidInjector
    fun contributeCategoryInfoFragment(): CategoryInfoFragment

    @ContributesAndroidInjector
    fun contributeCategoryCatalog(): CategoryCatalogFragment
}
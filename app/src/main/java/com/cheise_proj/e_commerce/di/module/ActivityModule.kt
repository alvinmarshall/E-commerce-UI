package com.cheise_proj.e_commerce.di.module

import com.cheise_proj.e_commerce.MainActivity
import com.cheise_proj.e_commerce.ui.bag.module.BagModule
import com.cheise_proj.e_commerce.ui.category.module.CategoriesModule
import com.cheise_proj.e_commerce.ui.category.module.CategoryModule
import com.cheise_proj.e_commerce.ui.category.ui.CategoriesFragment
import com.cheise_proj.e_commerce.ui.favorite.module.FavoriteModule
import com.cheise_proj.e_commerce.ui.product.module.ProductModule
import com.cheise_proj.e_commerce.ui.review.module.ReviewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(
        modules =
        [
            FragmentModule::class,
            CategoryModule::class,
            ProductModule::class,
            ReviewModule::class,
            FavoriteModule::class,
            BagModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity
}

@Module
interface FragmentModule {

    @ContributesAndroidInjector(modules = [CategoriesModule::class])
    fun contributeCategoriesFragment(): CategoriesFragment



}
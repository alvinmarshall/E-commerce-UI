package com.cheise_proj.e_commerce.ui.favorite.module

import com.cheise_proj.e_commerce.ui.favorite.FavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FavoriteModule {
    @ContributesAndroidInjector
    fun contributeFavoriteFragment(): FavoriteFragment
}
package com.cheise_proj.e_commerce.di.module

import com.cheise_proj.e_commerce.MainActivity
import com.cheise_proj.e_commerce.ui.category.CategoriesFragment
import com.cheise_proj.e_commerce.ui.category.module.CategoryModule
import com.cheise_proj.e_commerce.ui.main.Main2Fragment
import com.cheise_proj.e_commerce.ui.main.Main3Fragment
import com.cheise_proj.e_commerce.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun contributeMainActivity(): MainActivity
}

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun contributeMain2Fragment(): Main2Fragment

    @ContributesAndroidInjector
    fun contributeMain3Fragment(): Main3Fragment

    @ContributesAndroidInjector(modules = [CategoryModule::class])
    fun contributeCategoriesFragment(): CategoriesFragment

}
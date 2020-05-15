package com.cheise_proj.e_commerce.di.module

import com.cheise_proj.e_commerce.MainActivity
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
}
package com.cheise_proj.e_commerce.di.module.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule.Binders::class])
class ViewModelModule {

    @Module
    interface Binders {
        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    }


}
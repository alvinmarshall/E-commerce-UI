package com.cheise_proj.e_commerce.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.di.key.ViewModelKey
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import com.cheise_proj.e_commerce.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule.Binders::class])
class ViewModelModule {

    @Module
    interface Binders {
        @Binds
        fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    }


}
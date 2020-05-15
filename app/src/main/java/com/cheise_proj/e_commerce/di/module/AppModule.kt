package com.cheise_proj.e_commerce.di.module

import android.app.Application
import android.content.Context
import com.cheise_proj.e_commerce.di.module.auth.AuthModule
import com.cheise_proj.e_commerce.di.module.viewmodel.ViewModelModule
import com.cheise_proj.e_commerce.utils.INetworkState
import com.cheise_proj.e_commerce.utils.NetworkState
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.Binders::class, AuthModule::class, ViewModelModule::class])
class AppModule {

    @Module
    interface Binders {
        @Binds
        fun bindNetworkState(networkState: NetworkState): INetworkState
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext


}
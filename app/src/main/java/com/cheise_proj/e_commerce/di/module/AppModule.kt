package com.cheise_proj.e_commerce.di.module

import android.app.Application
import android.content.Context
import com.cheise_proj.e_commerce.di.IODispatcher
import com.cheise_proj.e_commerce.di.module.auth.AuthModule
import com.cheise_proj.e_commerce.di.module.remote.RemoteModule
import com.cheise_proj.e_commerce.di.module.repository.RepositoryModule
import com.cheise_proj.e_commerce.di.module.room.RoomModule
import com.cheise_proj.e_commerce.di.module.viewmodel.ViewModelModule
import com.cheise_proj.e_commerce.utils.INetworkState
import com.cheise_proj.e_commerce.utils.NetworkState
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes =
    [AppModule.Binders::class,
        AuthModule::class,
        RepositoryModule::class,
        RemoteModule::class,
        ViewModelModule::class,
        RoomModule::class
    ]
)
class AppModule {

    @Module
    interface Binders {
        @Binds
        fun bindNetworkState(networkState: NetworkState): INetworkState
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO


}
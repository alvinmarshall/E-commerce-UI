package com.cheise_proj.e_commerce.di

import android.app.Application
import com.cheise_proj.e_commerce.App
import com.cheise_proj.e_commerce.di.module.ActivityModule
import com.cheise_proj.e_commerce.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,ActivityModule::class])
interface AppComponent : AndroidInjector<App> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: App?)
}
package com.cheise_proj.e_commerce.di.module.room

import android.content.Context
import com.cheise_proj.e_commerce.data.db.LocalSource
import com.cheise_proj.e_commerce.data.db.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RoomModule.Binders::class])
class RoomModule {
    @Module
    interface Binders

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): LocalSource = LocalSource.getInstance(context)

    @Singleton
    @Provides
    fun provideProductDao(localSource: LocalSource): ProductDao = localSource.productDao()

    @Singleton
    @Provides
    fun provideCategoryDao(localSource: LocalSource): CategoryDao = localSource.categoryDao()

    @Singleton
    @Provides
    fun provideReviewDao(localSource: LocalSource): ReviewDao = localSource.reviewDao()

    @Singleton
    @Provides
    fun provideFavoriteDao(localSource: LocalSource): FavoriteDao = localSource.favoriteDao()

    @Singleton
    @Provides
    fun provideCartDao(localSource: LocalSource): CartDao = localSource.cartDao()

    @Singleton
    @Provides
    fun provideAddressDao(localSource: LocalSource): AddressDao = localSource.addressDao()

    @Singleton
    @Provides
    fun provideCardDao(localSource: LocalSource): CardDao = localSource.cardDao()

    @Singleton
    @Provides
    fun provideOrderDao(localSource: LocalSource): OrderDao = localSource.orderDao()

    @Singleton
    @Provides
    fun provideDeliveryDao(localSource: LocalSource): DeliveryDao = localSource.deliveryDao()

}
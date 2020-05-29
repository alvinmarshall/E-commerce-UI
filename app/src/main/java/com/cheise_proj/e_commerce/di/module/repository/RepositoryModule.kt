package com.cheise_proj.e_commerce.di.module.repository

import com.cheise_proj.e_commerce.data.repository.*
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Binders::class])
class RepositoryModule {
    @Module
    interface Binders {
        @Binds
        fun bindProductRepository(productRepository: ProductRepository): IProductRepository

        @Binds
        fun bindFavoriteRepository(favoriteRepository: FavoriteRepository): IFavoriteRepository

        @Binds
        fun bindCartRepository(cartRepository: CartRepository): ICartRepository

        @Binds
        fun bindAddressRepository(addressRepository: AddressRepository): IAddressRepository

        @Binds
        fun bindCardRepository(cardRepository: CardRepository): ICardRepository
    }
}
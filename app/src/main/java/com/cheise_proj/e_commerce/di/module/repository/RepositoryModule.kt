package com.cheise_proj.e_commerce.di.module.repository

import com.cheise_proj.e_commerce.data.repository.IProductRepository
import com.cheise_proj.e_commerce.data.repository.ProductRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Binders::class])
class RepositoryModule {
    @Module
    interface Binders {
        @Binds
        fun bindProductRepository(productRepository: ProductRepository): IProductRepository
    }
}
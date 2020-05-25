package com.cheise_proj.e_commerce.di.module.room

import android.content.Context
import androidx.room.Room
import com.cheise_proj.e_commerce.data.db.LocalSource
import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.db.dao.ReviewDao
import com.cheise_proj.e_commerce.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RoomModule.Binders::class])
class RoomModule {
    @Module
    interface Binders

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): LocalSource {
        return Room
            .databaseBuilder(context, LocalSource::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(localSource: LocalSource): ProductDao = localSource.productDao()

    @Singleton
    @Provides
    fun provideCategoryDao(localSource: LocalSource): CategoryDao = localSource.categoryDao()

    @Singleton
    @Provides
    fun provideReviewDao(localSource: LocalSource): ReviewDao = localSource.reviewDao()
}
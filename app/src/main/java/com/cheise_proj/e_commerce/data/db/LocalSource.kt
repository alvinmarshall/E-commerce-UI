package com.cheise_proj.e_commerce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cheise_proj.e_commerce.data.db.converter.ListConverter
import com.cheise_proj.e_commerce.data.db.dao.*
import com.cheise_proj.e_commerce.data.db.entity.*

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        ReviewEntity::class,
        FavoriteEntity::class,
        CartEntity::class,
        AddressEntity::class,
        CardEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)

abstract class LocalSource : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun reviewDao(): ReviewDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun cartDao(): CartDao
    abstract fun addressDao(): AddressDao
    abstract fun cardDao(): CardDao
}
package com.cheise_proj.e_commerce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cheise_proj.e_commerce.data.db.converter.ListConverter
import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.db.dao.ReviewDao
import com.cheise_proj.e_commerce.data.db.entity.CategoryEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductEntity
import com.cheise_proj.e_commerce.data.db.entity.ReviewEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)

abstract class LocalSource : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun reviewDao(): ReviewDao
}
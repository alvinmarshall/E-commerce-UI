package com.cheise_proj.e_commerce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheise_proj.e_commerce.data.db.dao.CategoryDao
import com.cheise_proj.e_commerce.data.db.dao.ProductDao
import com.cheise_proj.e_commerce.data.db.entity.CategoryEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalSource : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}
package com.cheise_proj.e_commerce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cheise_proj.e_commerce.data.db.converter.ListConverter
import com.cheise_proj.e_commerce.data.db.dao.*
import com.cheise_proj.e_commerce.data.db.entity.*
import com.cheise_proj.e_commerce.data.service.DeliveryService
import com.cheise_proj.e_commerce.utils.DATABASE_NAME
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        ReviewEntity::class,
        FavoriteEntity::class,
        CartEntity::class,
        AddressEntity::class,
        CardEntity::class,
        OrderEntity::class,
        DeliveryEntity::class
    ],
    version = 2,
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
    abstract fun orderDao(): OrderDao
    abstract fun deliveryDao(): DeliveryDao

    companion object {
        @Volatile
        private var INSTANCE: LocalSource? = null


        fun getInstance(context: Context): LocalSource = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, LocalSource::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                val handler = CoroutineExceptionHandler { _, throwable ->
                    Timber.i("err: room build: ${throwable.message}")
                }
                val scope = CoroutineScope(Dispatchers.IO).launch(handler) {
                    prePopulateDelivery(getInstance(context).deliveryDao())
                }
            })
            .build()

        private fun prePopulateDelivery(deliveryDao: DeliveryDao) {
            val deliveries = DeliveryService.getDeliveryTypes()
            Timber.i("prePopulate deliveries: $deliveries")
            deliveryDao.addDeliveries(deliveries)
        }


    }

}
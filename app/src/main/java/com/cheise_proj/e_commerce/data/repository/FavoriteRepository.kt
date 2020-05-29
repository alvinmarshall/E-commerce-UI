package com.cheise_proj.e_commerce.data.repository

import androidx.lifecycle.LiveData
import com.cheise_proj.e_commerce.data.db.dao.FavoriteDao
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import com.cheise_proj.e_commerce.utils.Error
import com.cheise_proj.e_commerce.utils.Result
import com.cheise_proj.e_commerce.utils.Success
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) :
    IFavoriteRepository {
    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) {
        Timber.i("addFavorite: $favoriteEntity")
        favoriteDao.addFavorite(favoriteEntity)
    }

    override  fun getProductFavorite(): Flow<List<ProductWithFavorite>> {
        return favoriteDao.getProductFavorite()
    }

    override suspend fun removeFavorite(identifier: Int?) {
        Timber.i("removeFavorite with identifier: $identifier")
        favoriteDao.removeFavorite(identifier)
    }

    override suspend fun getFavorites(): Flow<List<FavoriteEntity>> {
        return favoriteDao.getFavorite()
    }

}
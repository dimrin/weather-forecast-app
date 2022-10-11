package com.vadim.weatherforecastapp.repository

import com.vadim.weatherforecastapp.data.WeatherDAO
import com.vadim.weatherforecastapp.model.FavoriteEntity
import com.vadim.weatherforecastapp.model.UnitEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDAO) {

    fun getFavorites(): Flow<List<FavoriteEntity>> = weatherDao.getFavorites()

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) = weatherDao.insertFavorite(favoriteEntity)

    suspend fun updateFavorite(favoriteEntity: FavoriteEntity) = weatherDao.updateFavorite(favoriteEntity)

    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) = weatherDao.deleteFavorite(favoriteEntity)

    suspend fun getFavoriteById(city: String): FavoriteEntity = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<UnitEntity>> = weatherDao.getUnits()

    suspend fun insertUnit(unit: UnitEntity) = weatherDao.insertUnit(unit)

    suspend fun updateUnit(unit: UnitEntity) = weatherDao.updateUnit(unit)

    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()

    suspend fun deleteUnit(unit: UnitEntity) = weatherDao.deleteUnit(unit)

}
package com.vadim.weatherforecastapp.data

import androidx.room.*
import com.vadim.weatherforecastapp.model.FavoriteEntity
import com.vadim.weatherforecastapp.model.UnitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM fav_tbl WHERE  city =:city")
    suspend fun getFavById(city: String): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM settings_tbl")
    fun getUnits(): Flow<List<UnitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unitEntity: UnitEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unitEntity: UnitEntity)

    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unitEntity: UnitEntity)
}
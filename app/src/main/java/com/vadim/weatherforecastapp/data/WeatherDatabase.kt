package com.vadim.weatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadim.weatherforecastapp.model.FavoriteEntity
import com.vadim.weatherforecastapp.model.UnitEntity

@Database(entities = [FavoriteEntity::class, UnitEntity::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}
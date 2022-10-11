package com.vadim.weatherforecastapp.screens.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadim.weatherforecastapp.model.FavoriteEntity
import com.vadim.weatherforecastapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository): ViewModel() {
    private val _favList = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favList = _favList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isNullOrEmpty()){
                    Log.d("TAG", ": Empty favs")
                } else {
                    _favList.value = listOfFavs
                    Log.d("TAG", ": ${favList.value}")
                }
            }
        }
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch { repository.insertFavorite(favoriteEntity) }

    fun updateFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch { repository.updateFavorite(favoriteEntity) }

    fun deleteFavorite(favoriteEntity: FavoriteEntity) = viewModelScope.launch { repository.deleteFavorite(favoriteEntity) }

}
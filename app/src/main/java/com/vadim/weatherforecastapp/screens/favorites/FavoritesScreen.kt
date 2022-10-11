package com.vadim.weatherforecastapp.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vadim.weatherforecastapp.model.FavoriteEntity
import com.vadim.weatherforecastapp.navigation.WeatherScreens
import com.vadim.weatherforecastapp.widgets.WeatherAppBar

@Composable
fun FavoritesScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            title = "Favorite Cities",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) {
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoriteViewModel.favList.collectAsState().value

                LazyColumn {
                    items(items = list) {
                        CityRow(
                            favorite = it,
                            navController = navController,
                            favoriteViewModel = favoriteViewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favorite: FavoriteEntity,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    Button(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB2DFDB)),
        onClick = { navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}") }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 4.dp))

            Surface(modifier = Modifier.padding(0.dp), shape = CircleShape) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )
            }

            IconButton(onClick = { favoriteViewModel.deleteFavorite(favorite) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.Red.copy(alpha = 0.3f)
                )
            }

        }
    }
}

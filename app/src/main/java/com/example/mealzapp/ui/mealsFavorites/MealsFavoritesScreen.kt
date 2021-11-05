package com.example.mealzapp.ui.mealsFavorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.BarElement


@Composable
fun MealsFavoritesScreen(
    onClickMeal: (String) -> Unit,
    onClickNav: () -> Unit
) {
    // attach the viewmodel to the composable (Live as long as the composable)

    val viewModel: MealsFavoritesViewModel = viewModel()
    val meals = viewModel.mealsFavoritesState.value
    val mealsList = meals.toList()

    Scaffold(topBar = {
        AppBar(
            "My Menu",
            icon = Icons.Default.ArrowBack,
            onClickNav
        )
    }) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(meals.toList()) { meal ->

                BarElement(
                    name = meal.name?:"",
                    description = "",
                    imageUrl = meal.imageUrl + "/preview",
                    elementId = meal.id?:"",
                    onClickNav = onClickMeal
                )
            }
        }

    }

}


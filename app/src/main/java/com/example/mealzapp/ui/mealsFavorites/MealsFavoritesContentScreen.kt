package com.example.mealzapp.ui.mealsFavorites

import androidx.compose.foundation.layout.Arrangement
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
fun MealsFavoritesContentScreen(
    onClickMeal: (String) -> Unit,
    filterValue: String = ""
) {
    // attach the viewmodel to the composable (Live as long as the composable)

    val viewModel: MealsFavoritesViewModel = viewModel()
    val meals = viewModel.mealsFavoritesState.value

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.Top

        ) {
        items(meals) { meal ->

            if ((meal.name?.contains(filterValue, ignoreCase = true)?: false) or (filterValue == "")
            ) {

                BarElement(
                    name = meal.name ?: "",
                    description = "",
                    imageUrl = meal.imageUrl + "/preview",
                    elementId = meal.id ?: "",
                    onClickNav = onClickMeal
                )
            }
        }
    }

}


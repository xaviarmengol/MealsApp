package com.example.mealzapp.ui.mealsFavorites


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.model.FavoritesMealsRepository
import com.example.model.response.MealResponse


class MealsFavoritesViewModel () : ViewModel() {

    private val repository: FavoritesMealsRepository = FavoritesMealsRepository.getInstance()

    val mealsFavoritesState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    init {
        mealsFavoritesState.value = repository.meals
    }

}
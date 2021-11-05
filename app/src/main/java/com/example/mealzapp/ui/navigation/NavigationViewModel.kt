package com.example.mealzapp.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.model.FavoritesMealsRepository
import com.example.model.MenusCompleteRepository
import com.example.model.response.MealRecipeArrangedResponse
import com.example.model.response.MealResponse

class NavigationViewModel : ViewModel() {

    //private val repository: MenusRepository = MenusRepository.getInstance()
    private val repositoryMenus: MenusCompleteRepository = MenusCompleteRepository.getInstance()
    private val repositoryFavorite : FavoritesMealsRepository = FavoritesMealsRepository.getInstance()

    fun addMealToCurrentMenu(meal : MealRecipeArrangedResponse) {
        if((repositoryMenus.menuActiveId > -1) and (repositoryMenus.mealActiveId > -1)) {
            repositoryMenus.addMeal(meal)
        }
        resetMealActive()
    }

    fun addMealToFavorites(meal : MealResponse) {
        repositoryFavorite.add(meal)
    }


    private fun resetMealActive() {
        repositoryMenus.mealActiveId=-1
    }


}
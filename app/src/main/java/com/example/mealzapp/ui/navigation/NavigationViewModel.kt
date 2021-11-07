package com.example.mealzapp.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.model.MenusCompleteRepository
import com.example.model.response.MealRecipeArrangedResponse

class NavigationViewModel : ViewModel() {

    //private val repository: MenusRepository = MenusRepository.getInstance()
    private val repositoryMenus: MenusCompleteRepository = MenusCompleteRepository.getInstance()

    fun addMealToCurrentMenu(meal : MealRecipeArrangedResponse) {
        if((repositoryMenus.menuActiveId > -1) and (repositoryMenus.mealActiveId > -1)) {
            repositoryMenus.addMeal(meal)
        }
        resetMealActive()
    }

    private fun resetMealActive() {
        repositoryMenus.mealActiveId=-1
    }


}
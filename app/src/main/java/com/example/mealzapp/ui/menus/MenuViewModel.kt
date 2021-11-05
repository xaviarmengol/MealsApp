package com.example.mealzapp.ui.menus

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealzapp.ui.ingredientsCart.IngredientsCartViewModel
import com.example.model.*
import com.example.model.response.*

class MenuViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MenuViewModel(application) as T
}


class MenuViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: MenusCompleteRepository = MenusCompleteRepository.getInstance()

    val menusState: MutableState<List<MenuMealsComplete>> = mutableStateOf(listOf(MenuMealsComplete()))
    val activeMenuState : MutableState<MenuMealsComplete> = mutableStateOf(MenuMealsComplete())

    // TODO: View model imported from another Viewmodel. Is it right?
    val viewModelCart = IngredientsCartViewModel()

    var menuAct = 0
    var mealAct = -1

    init {
        menusState.value = repository.menus
        activeMenuState.value = menusState.value[0]
    }

    fun setMenuName(newName: String) {
        repository.setMenuName(newName)
        activeMenuState.value = menusState.value[0]
    }

    fun getMenuName() : String {
        return activeMenuState.value.name
    }

    fun setMealActive(mealNum: Int) {
        repository.menuActiveId = 0
        repository.mealActiveId = mealNum

        menuAct = repository.menuActiveId
        mealAct = repository.mealActiveId
    }

    fun resetMeal(mealNum: Int) {

        // Delete all ingredients from Cart

        val mealDetails = menusState.value[menuAct].meals[mealNum]
        val mealImage = mealDetails.strMealThumb
        for (ingredient in menusState.value[menuAct].meals[mealNum].ingredientsRecipe) {
            val ingredientName = ingredient.name
            val ingredientMeasure = ingredient.measure

            viewModelCart.delete(mealImage?:"", ingredientName?:"", ingredientMeasure?:"")
        }

        // Delete meal from Menu
        menusState.value[menuAct].meals[mealNum] = MealRecipeArrangedResponse()
    }

    fun isMealFull(mealNum: Int): Boolean {
        return menusState.value[menuAct].meals[mealNum].idMeal != ""
    }

}
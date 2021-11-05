package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolderWithNoParameters
import com.example.model.response.MealRecipeArrangedResponse

data class MenuMealsComplete (
    val meals: MutableList<MealRecipeArrangedResponse, > =
        mutableListOf(MealRecipeArrangedResponse(),MealRecipeArrangedResponse(),MealRecipeArrangedResponse()),
    var name : String = ""
)

class MenusCompleteRepository (
    val menus : MutableList<MenuMealsComplete> = mutableListOf(MenuMealsComplete()),
    var menuActiveId : Int = 0,
    var mealActiveId : Int = -1
){

    fun addMeal(meal : MealRecipeArrangedResponse) {
        menus[menuActiveId].meals[mealActiveId] = meal
    }

    fun setMenuName(name: String) {
        menus[menuActiveId].name = name
    }

    fun addMenu(menuMeals : MenuMealsComplete) {
        menus.add(menuMeals)
    }

    fun removeMeal(menuNum: Int, mealNum: Int){
        menus[menuNum].meals[mealNum] = MealRecipeArrangedResponse()
    }

    fun removeMenu(menuMeals: MenuMealsComplete){
        menus.remove(menuMeals)
    }

    // Convert into singleton
    companion object : SingletonHolderWithNoParameters<MenusCompleteRepository>(::MenusCompleteRepository)
}
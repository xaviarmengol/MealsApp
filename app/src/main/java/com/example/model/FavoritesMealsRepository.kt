package com.example.model

import android.util.Log
import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.mealzapp.ui.utils.SingletonHolderWithNoParameters
import com.example.model.response.MealResponse

class FavoritesMealsRepository (val meals : MutableList<MealResponse> = mutableListOf()){

    fun togle(meal: MealResponse) {
        if (contains(meal)) {
            remove(meal)
        } else {
            add(meal)
        }
    }

    fun add(meal : MealResponse) {
        meals.add(meal)
    }

    fun remove(meal: MealResponse) {
        meals.remove(meal)
    }

    fun contains(meal: MealResponse) : Boolean {
        //Log.i("RepositoryFav", "contains: ${meal in meals}")
        return meal in meals
    }

    // Convert into singleton
    companion object : SingletonHolderWithNoParameters<FavoritesMealsRepository>(::FavoritesMealsRepository)
}
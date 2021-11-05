package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.model.api.MealsDBWebService
import com.example.model.response.MealResponse
import com.example.model.response.MealsResponse

class MealsWithIngredientRepository (private val webService : MealsDBWebService = MealsDBWebService()) {

    private var cachedMealsWithIngredient: List<MealResponse?> = emptyList()

    suspend fun getMealsWithIngredient(ingredientId : String) : MealsResponse {
        val meals = webService.getMealsWithIngredient(ingredientId)
        cachedMealsWithIngredient = meals.meals
        return meals
    }

    fun getCachedMealById(ingredientId : String) : MealResponse? {
        return cachedMealsWithIngredient.firstOrNull() {
                meal -> meal?.id == ingredientId
        }
    }
    // Convert into singleton
    companion object : SingletonHolder<MealsWithIngredientRepository, MealsDBWebService>(::MealsWithIngredientRepository)
}
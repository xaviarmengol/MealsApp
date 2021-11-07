package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.model.api.MealsCachedWebService
import com.example.model.response.MealResponse
import com.example.model.response.MealsResponse

class MealsWithIngredientRepository (private val webService : MealsCachedWebService) {

    private var cachedMealsWithIngredient: List<MealResponse?> = emptyList()

    suspend fun getMealsWithIngredient(ingredientId : String) : MealsResponse {
        val meals = webService.getMealsWithIngredient(ingredientId)
        cachedMealsWithIngredient = meals.meals
        return meals
    }

    fun getCachedMealById(ingredientId : String) : MealResponse? {
        return cachedMealsWithIngredient.firstOrNull {
                meal -> meal?.id == ingredientId
        }
    }
    // Convert into singleton
    companion object : SingletonHolder<MealsWithIngredientRepository, MealsCachedWebService>(::MealsWithIngredientRepository)
}
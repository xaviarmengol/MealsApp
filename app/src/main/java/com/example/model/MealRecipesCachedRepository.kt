package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.model.api.MealsCachedWebService
import com.example.model.response.*

class MealRecipesCachedRepository (private val webService : MealsCachedWebService){

    private var cachedRecipes: List<MealRecipeResponse> = emptyList()

    suspend fun getMealRecipes(mealId : String) : MealRecipesResponse {
        val recipes = webService.getMealRecipes(mealId)
        cachedRecipes = recipes.recipes
        return recipes
    }

    fun getCachedMealRecipe() : MealRecipeResponse? {
        return cachedRecipes[0]
    }

    // Convert into singleton
    companion object : SingletonHolder<MealRecipesCachedRepository, MealsCachedWebService>(::MealRecipesCachedRepository)

}
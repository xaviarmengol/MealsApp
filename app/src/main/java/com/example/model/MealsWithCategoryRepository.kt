package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.model.api.MealsDBWebService
import com.example.model.response.MealResponse
import com.example.model.response.MealsResponse

class MealsWithCategoryRepository (private val webService : MealsDBWebService = MealsDBWebService()) {

    private var cachedMealsWithCategory: List<MealResponse?> = emptyList()

    suspend fun getMealsWithCategory(CategoryId : String) : MealsResponse {
        val meals = webService.getMealsWithCategory(CategoryId)
        cachedMealsWithCategory = meals.meals
        return meals
    }

    fun getCachedMealById(CategoryId : String) : MealResponse? {
        return cachedMealsWithCategory.firstOrNull() {
                meal -> meal?.id == CategoryId
        }
    }
    // Convert into singleton
    companion object : SingletonHolder<MealsWithCategoryRepository, MealsDBWebService>(::MealsWithCategoryRepository)
}
package com.example.model

import android.content.Context
import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.mealzapp.ui.utils.readFromFile
import com.example.mealzapp.ui.utils.writeToFile
import com.example.model.api.MealsDBWebService
import com.example.model.response.CategoriesResponse
import com.example.model.response.CategoryResponse
import com.google.gson.Gson

class MealsCategoriesRepository(private val webService : MealsDBWebService = MealsDBWebService()) {

    private var cachedMealsCategories: List<CategoryResponse> = emptyList()
    private var categories : CategoriesResponse = CategoriesResponse()

    val gson = Gson()

    suspend fun getCategories() : CategoriesResponse {
        val meals = webService.getMealsCategories()
        cachedMealsCategories = meals.categories
        return meals
    }

    fun getCachedCategoryById(id : String) : CategoryResponse? {
        return cachedMealsCategories.firstOrNull() {
            meal -> meal.id == id
        }
    }


    fun saveToLocalFile(context : Context) {
        if (cachedMealsCategories.isNotEmpty()) {
            val jsonText = gson.toJson(CategoriesResponse(cachedMealsCategories), CategoriesResponse::class.java)
            if ((jsonText != null) and (jsonText != "")) {
                writeToFile("CategoriesRepository.txt", jsonText, context)
            }
        }
    }

    fun readFromLocalFile(context: Context) : CategoriesResponse {
        val jsonString = readFromFile("CategoriesRepository.txt", context) ?:""
        if (jsonString != "") {
            categories = gson.fromJson(jsonString, CategoriesResponse::class.java)
        } else {
            categories = CategoriesResponse(emptyList())
        }
        cachedMealsCategories = categories.categories
        return categories
    }



    // Convert into singleton
    companion object : SingletonHolder<MealsCategoriesRepository, MealsDBWebService>(::MealsCategoriesRepository)
}


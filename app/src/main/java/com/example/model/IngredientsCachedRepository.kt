package com.example.model

import android.content.Context
import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.mealzapp.ui.utils.readFromFile
import com.example.mealzapp.ui.utils.writeToFile
import com.example.model.api.MealsDBCachedWebService
import com.example.model.response.IngredientResponse
import com.example.model.response.IngredientsResponse
import com.google.gson.Gson

class IngredientsCachedRepository (private val webService : MealsDBCachedWebService) {

    private var cachedIngredients: List<IngredientResponse> = emptyList()
    private var ingredients : IngredientsResponse = IngredientsResponse()

    val gson = Gson()

    suspend fun getIngredients() : IngredientsResponse {
        ingredients = webService.getIngredients()
        cachedIngredients = ingredients.ingredients
        return ingredients
    }

    fun getCachedIngredientByName(name : String) : IngredientResponse? {
        return cachedIngredients.firstOrNull() {
                ingredient -> ingredient.name == name
        }
    }

    fun saveToLocalFile(context : Context) {
        if (cachedIngredients.isNotEmpty()) {
            val jsonText = gson.toJson(IngredientsResponse(cachedIngredients), IngredientsResponse::class.java)
            if ((jsonText != null) and (jsonText != "")) {
                writeToFile("IngredientsRepository.txt", jsonText, context)
            }
        }
    }

    fun readFromLocalFile(context: Context) : IngredientsResponse {
        val jsonString = readFromFile("IngredientsRepository.txt", context)?:""
        if (jsonString != "") {
            ingredients = gson.fromJson(jsonString, IngredientsResponse::class.java)
        } else {
            ingredients = IngredientsResponse(emptyList())
        }
        cachedIngredients = ingredients.ingredients
        return ingredients
    }


    // Convert into singleton
    companion object : SingletonHolder<IngredientsCachedRepository, MealsDBCachedWebService>(::IngredientsCachedRepository)
}
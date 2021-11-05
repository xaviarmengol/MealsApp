package com.example.model


import android.util.Log
import com.example.mealzapp.ui.utils.SingletonHolderWithNoParameters
import com.example.model.response.IngredientMealImageNameMeasure

class IngredientsCartRepository (val ingredients : MutableSet<IngredientMealImageNameMeasure> = mutableSetOf()){


    fun togle(ingredient: IngredientMealImageNameMeasure) {

        if (contains(ingredient)) {
            remove(ingredient)
        } else {
            add(ingredient)
        }
    }

    fun togle(mealName: String, name: String, measure: String) {

        val ingredient = IngredientMealImageNameMeasure(mealName,name,measure)

        if (contains(ingredient)) {
            remove(ingredient)
        } else {
            add(ingredient)
        }
    }


    fun add(ingredient : IngredientMealImageNameMeasure) {
        ingredients.add(ingredient)
    }

    fun add(ingredientCode : String) {
        val components = ingredientCode.split('^')
        val ingredientObject = IngredientMealImageNameMeasure( components[0],  components[1],  components[2])
        ingredients.add(ingredientObject)
    }

    fun add(mealImage: String, name: String, measure: String) {
        ingredients.add(IngredientMealImageNameMeasure(mealImage, name, measure))
    }

    fun remove(ingredient: IngredientMealImageNameMeasure) {
        var removed = false
        removed = ingredients.remove(ingredient)
        Log.i("RepositoryFav", "removed: $removed - $ingredient ")
    }

    fun contains(ingredient: IngredientMealImageNameMeasure) : Boolean {
        //Log.i("RepositoryFav", "contains: ${meal in meals}")
        return ingredient in ingredients
    }

    fun contains(mealImage: String, name: String, measure: String) : Boolean {
        //Log.i("RepositoryFav", "contains: ${meal in meals}")
        return IngredientMealImageNameMeasure(mealImage, name, measure) in ingredients
    }

    // Convert into singleton
    companion object : SingletonHolderWithNoParameters<IngredientsCartRepository>(::IngredientsCartRepository)
}
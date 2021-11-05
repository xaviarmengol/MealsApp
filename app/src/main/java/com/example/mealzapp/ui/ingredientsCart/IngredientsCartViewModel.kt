package com.example.mealzapp.ui.ingredientsCart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.model.IngredientsCartRepository
import com.example.model.response.IngredientMealImageNameMeasure



class IngredientsCartViewModel () : ViewModel() {

    private val repository: IngredientsCartRepository = IngredientsCartRepository.getInstance()

    val ingredientsCartState: MutableState<List<IngredientMealImageNameMeasure>> = mutableStateOf(emptyList())

    init {
        ingredientsCartState.value = repository.ingredients.toList()
    }

    fun delete(mealName : String, ingredient: String, measure: String) {
        val ingredientObject = IngredientMealImageNameMeasure( mealName,  ingredient,  measure)
        repository.remove(ingredientObject)
    }

    fun delete(ingredientCode : String) {
        val components = ingredientCode.split('^')
        val ingredientObject = IngredientMealImageNameMeasure( components[0],  components[1],  components[2])
        repository.remove(ingredientObject)

        ingredientsCartState.value = repository.ingredients.toList()
        //Log.i("Repository", "delete: ${IngredientsCartState.value}")
    }

}
package com.example.mealzapp.ui.mealRecipe

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.*
import com.example.model.api.MealsCachedWebService
import com.example.model.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealRecipesCachedViewModelFactory(private val mealId: String, private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MealRecipeCachedViewModel(mealId, application) as T
}


class MealRecipeCachedViewModel (mealId: String, val app: Application) : AndroidViewModel(app) {

    private val repository: MealRecipesCachedRepository = MealRecipesCachedRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )
    //private val repositoryFavorites : FavoritesMealsRepository = FavoritesMealsRepository.getInstance()
    private val repositoryIngredientsCart: IngredientsCartRepository = IngredientsCartRepository.getInstance()

    private val repositoryDBFavorites: FavoritesMealsDBRepository = FavoritesMealsDBRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )


    private val mealsRecipeState: MutableState<List<MealRecipeResponse>> = mutableStateOf(emptyList())
    val mealsRecipeArrangedState: MutableState<MealRecipeArrangedResponse> =
        mutableStateOf(MealRecipeArrangedResponse())

    val meal: MutableState<MealResponse> = mutableStateOf(MealResponse())
    val recipeSteps : MutableState<List<String>> = mutableStateOf(emptyList())
    val titleScreen : MutableState <String> = mutableStateOf("")

    val isFavorite : MutableState <Boolean> = mutableStateOf(false)


    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                mealsRecipeState.value = getMealRecipe(mealId)
                titleScreen.value = "${mealsRecipeState.value[0].strMeal} recipe"
                mealsRecipeArrangedState.value = convertToRecipeArranged(mealsRecipeState.value)

                recipeSteps.value = mealsRecipeArrangedState.value.strInstructions?.split('\n') ?: listOf("")
                meal.value = MealResponse(
                    mealsRecipeArrangedState.value.idMeal?:"",
                    mealsRecipeArrangedState.value.strMeal?:"",
                    mealsRecipeArrangedState.value.strMealThumb?:"")

                isFavorite.value = repositoryDBFavorites.contains(meal.value)
            } catch (e: Exception) {
                Log.e("Repository Meals", "Exception thrown by repository: $e")
            }

        }

    }

    private suspend fun getMealRecipe(mealId : String): List<MealRecipeResponse> {
        return repository.getMealRecipes(mealId).recipes
    }

    fun togle() {
        repositoryDBFavorites.togle(meal.value)
        isFavorite.value = repositoryDBFavorites.contains(meal.value)
    }

    fun addIngredientToCart(mealName: String, name: String, mesure: String ) {
        repositoryIngredientsCart.add(mealName, name, mesure)
    }

    fun ingredientIsInCart(mealName: String, name: String, mesure: String ) : Boolean {
        //repositoryIngredientsCart.add(mealName, name, mesure)
        return repositoryIngredientsCart.contains(mealName, name, mesure)
    }

    fun togleIngredient(mealName: String, name: String, mesure: String ) {
        repositoryIngredientsCart.togle(mealName, name, mesure)
    }



}
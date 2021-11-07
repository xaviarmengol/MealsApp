package com.example.mealzapp.ui.mealsWithIngredient

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.FavoritesMealsDBRepository
import com.example.model.FavoritesMealsRepository
import com.example.model.MealsWithIngredientRepository
import com.example.model.api.MealsCachedWebService
import com.example.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsWithIngredientsViewModelFactory(private val ingredientName: String, private val app: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MealsWithIngredientViewModel(ingredientName, app) as T
}


class MealsWithIngredientViewModel (ingredientName : String, app: Application) : AndroidViewModel(app) {

    private val repository: MealsWithIngredientRepository = MealsWithIngredientRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )
    //private val repositoryFavorite : FavoritesMealsRepository = FavoritesMealsRepository.getInstance()

    private val repositoryDBFavorites: FavoritesMealsDBRepository = FavoritesMealsDBRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )

    val mealsWithIngredientState: MutableState<List<MealResponse?>> = mutableStateOf(emptyList())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            mealsWithIngredientState.value = getMealsWithIngredient(ingredientName)
        }
    }

    private suspend fun getMealsWithIngredient(ingredientId : String): List<MealResponse?> {
        return repository.getMealsWithIngredient(ingredientId).meals
    }

    fun togleToFavorites(meal: MealResponse) {
        repositoryDBFavorites.togle(meal)
    }

    fun isFavorite(meal: MealResponse) : Boolean {
        return repositoryDBFavorites.contains(meal)
    }
}
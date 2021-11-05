package com.example.mealzapp.ui.mealWithCategory

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.FavoritesMealsRepository
import com.example.model.MealsWithCategoryRepository
import com.example.model.api.MealsDBWebService
import com.example.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsWithCategoryViewModelFactory(private val CategoryName: String, private val app: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MealsWithCategoryViewModel(CategoryName, app) as T
}


class MealsWithCategoryViewModel (categoryName : String, app: Application) : AndroidViewModel(app) {

    private val repository: MealsWithCategoryRepository = MealsWithCategoryRepository.getInstance(
        MealsDBWebService()
    )

    private val repositoryFavorite : FavoritesMealsRepository = FavoritesMealsRepository.getInstance()

    val mealsWithCategoryState: MutableState<List<MealResponse?>> = mutableStateOf(emptyList())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            mealsWithCategoryState.value = getMealsWithCategory(categoryName)
        }
    }

    private suspend fun getMealsWithCategory(CategoryId : String): List<MealResponse?> {
        return repository.getMealsWithCategory(CategoryId).meals
    }

    fun togleToFavorites(meal: MealResponse) {
        repositoryFavorite.togle(meal)
    }

    fun isFavorite(meal: MealResponse) : Boolean {
        return repositoryFavorite.contains(meal)
    }
}
package com.example.mealzapp.ui.mealsFavorites


import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealzapp.ui.mealsCategories.MealsCategoriesCachedViewModel
import com.example.model.FavoritesMealsDBRepository
import com.example.model.FavoritesMealsRepository
import com.example.model.api.MealsCachedWebService
import com.example.model.response.MealResponse


class MealsFavoritesViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MealsFavoritesViewModel(application) as T
}


class MealsFavoritesViewModel (val app: Application) : AndroidViewModel(app) {

    //private val repository: FavoritesMealsRepository = FavoritesMealsRepository.getInstance()

    private val repositoryDB: FavoritesMealsDBRepository = FavoritesMealsDBRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )

    val mealsFavoritesState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    init {
        mealsFavoritesState.value = repositoryDB.meals
    }

}
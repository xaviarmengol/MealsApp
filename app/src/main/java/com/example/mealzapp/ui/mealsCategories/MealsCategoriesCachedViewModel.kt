package com.example.mealzapp.ui.mealsCategories

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.MealsCategoriesCachedRepository
import com.example.model.api.MealsCachedWebService
import com.example.model.response.CategoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MealsCategoriesCachedViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MealsCategoriesCachedViewModel(application) as T
}


class MealsCategoriesCachedViewModel (val app: Application) : AndroidViewModel(app) {

    private val repository: MealsCategoriesCachedRepository = MealsCategoriesCachedRepository.getInstance(
        MealsCachedWebService(app.applicationContext)
    )



    val categoriesState: MutableState<List<CategoryResponse>> = mutableStateOf(emptyList<CategoryResponse>())
    val updatedFromNetwork: MutableState<Boolean> = mutableStateOf(false)
    val savedInLocalFile: MutableState<Boolean> = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                categoriesState.value = getCategories()
                updatedFromNetwork.value = true
            } catch (e: Exception) {
                Log.e("Repository Categories", "Exception thrown by repository: $e")
            }
        }
    }

    private suspend fun getCategories(): List<CategoryResponse> {
        return repository.getCategories().categories
    }




    fun readFromLocalFile(context: Context) {
        categoriesState.value = repository.readFromLocalFile(context).categories
    }

    fun readFromLocalFileIfNotUpdated(context : Context) {
        readFromLocalFile(context)
    }

    fun saveToLocalFile(context: Context) {
        repository.saveToLocalFile(context)
    }

    fun setFileSaved() {
        savedInLocalFile.value = true
    }


}
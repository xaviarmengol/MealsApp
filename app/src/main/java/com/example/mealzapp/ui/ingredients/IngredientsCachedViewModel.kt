package com.example.mealzapp.ui.ingredients

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.IngredientsCachedRepository
import com.example.model.api.MealsDBCachedWebService
import com.example.model.response.IngredientResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IngredientsCachedViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = IngredientsCachedViewModel(application) as T
}

class IngredientsCachedViewModel (val app: Application) : AndroidViewModel(app) {

    val repository: IngredientsCachedRepository =
        IngredientsCachedRepository.getInstance(MealsDBCachedWebService(app.applicationContext))

    val ingredientsState: MutableState<List<IngredientResponse>> = mutableStateOf(emptyList())
    val updatedFromNetwork: MutableState<Boolean> = mutableStateOf(false)
    val savedInLocalFile: MutableState<Boolean> = mutableStateOf(false)

    init {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                ingredientsState.value = getIngredients()
                updatedFromNetwork.value = true
            } catch (e: Exception) {
                Log.e("RepositoryIngredients", "Exception thrown by repository: ${e.toString()}")
            }
        }
    }

    private suspend fun getIngredients(): List<IngredientResponse> {

        return repository.getIngredients().ingredients
    }

}
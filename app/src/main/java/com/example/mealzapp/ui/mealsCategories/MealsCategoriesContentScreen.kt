package com.example.mealzapp.ui.mealsCategories

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.ingredients.IngredientsCachedViewModel
import com.example.mealzapp.ui.ingredients.IngredientsCachedViewModelFactory
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.BarElement

@Composable
fun MealsCategoriesContentScreen(onClickCategory: (String) -> Unit, filterValue: String = "", app : Application) {
    // attach the viewmodel to the composable (Live as long as the composable)

    //val viewModel: MealsCategoriesViewModel = viewModel()
    val viewModel: MealsCategoriesCachedViewModel = viewModel(factory = MealsCategoriesCachedViewModelFactory(app))

    val categories = viewModel.categoriesState.value
    val updated = viewModel.updatedFromNetwork.value
    val savedInLocalFile = viewModel.savedInLocalFile.value
    val context = LocalContext.current
    val jobId = 0

    /*
    LaunchedEffect(jobId) {
        viewModel.readFromLocalFileIfNotUpdated(context)
    }

    if (updated and !savedInLocalFile) {
        viewModel.saveToLocalFile(context)
        viewModel.setFileSaved()
        Log.i("file saved", "Meals Category Screen: Saved local file")
    }
     */

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(categories) { category ->
            if (category.name.contains(filterValue, ignoreCase = true) or (filterValue == "")
            ) {
                BarElement(
                    name = category.name,
                    description = category.description,
                    imageUrl = category.imageUrl,
                    elementId = category.name,
                    onClickNav = onClickCategory
                )
            }
        }
    }


}

package com.example.mealzapp.ui.ingredients

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.BarElement


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
//connected : Boolean, cacheDir : String
fun IngredientsContentScreen(onClickIngredient: (String) -> Unit, filterValue: String = "", app : Application) {

    //val viewModel: IngredientsViewModel = viewModel()
    val viewModel: IngredientsCachedViewModel = viewModel(factory = IngredientsCachedViewModelFactory(app))


    val ingredients = viewModel.ingredientsState.value
    val updated = viewModel.updatedFromNetwork.value
    val savedInLocalFile = viewModel.savedInLocalFile.value
    val context = LocalContext.current
    val jobId = 0

    LaunchedEffect(jobId) {
        //viewModel.initViewModel(context)
        //viewModel.readFromLocalFileIfNotUpdated(context)
    }

    /*
    if (updated and !savedInLocalFile) {
        viewModel.saveToLocalFile(context)
        viewModel.setFileSaved()
        Log.i("file saved", "IngredientsScreen: Saved local file")
    }
     */
    
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(ingredients) { ingredient ->
            if ((ingredient.name?.contains(filterValue, ignoreCase = true)
                    ?: false) or (filterValue == "")
            ) {
                BarElement(
                    name = ingredient.name ?: "",
                    imageUrl = "https://www.themealdb.com/images/ingredients/${ingredient.name ?: ""}-small.png",
                    elementId = ingredient.name ?: "",
                    description = ingredient.description ?: "",
                    onClickNav = onClickIngredient,
                )
            }
        }
    }


}

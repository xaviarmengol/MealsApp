package com.example.mealzapp.ui.ingredientsCart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.BarElementDeletable

@Composable
fun IngredientsCartScreen(
    onClickIngredientCart: (String) -> Unit,
    onClickNav: () -> Unit
) {

    val viewModel: IngredientsCartViewModel = viewModel()
    val ingredientsCart = viewModel.ingredientsCartState.value

    var image : String = ""

    Scaffold(topBar = {
        AppBar(
            "My ingredients cart",
            icon = Icons.Default.ArrowBack,
            onClickNav
        )
    }) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(ingredientsCart) { ingredient ->

                 val ingredientString = "${ingredient.mealImage}^${ingredient.name}^${ingredient.measure}"

                BarElementDeletable(
                    name = ingredient.name?:"",
                    description = "Measure: ${ingredient.measure?:""}",
                    imageUrl = "https://www.themealdb.com/images/ingredients/${ingredient.name?:""}-small.png",
                    elementId = ingredient.mealImage?:"",
                    onClickNav = onClickIngredientCart,
                    iconRight = Icons.Default.Delete,
                    onClickRight = {viewModel.delete(ingredientString)}
                )
            }
        }

    }

}
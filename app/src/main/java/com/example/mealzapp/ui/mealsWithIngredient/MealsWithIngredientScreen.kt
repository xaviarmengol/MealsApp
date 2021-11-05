package com.example.mealzapp.ui.mealsWithIngredient


import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.BarElement
import com.example.mealzapp.ui.utils.FindBar
import com.example.model.response.MealResponse


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MealsWithIngredientsScreen(
    ingredientName: String,
    onClickNav: () -> Unit,
    onClickBar : (String) -> Unit,
    onClickBarIcon: (MealResponse) -> Unit = {},
    app: Application
) {
    // attach the viewmodel to the composable (Live as long as the composable)

    val viewModel: MealsWithIngredientViewModel =
        viewModel(factory = MealsWithIngredientsViewModelFactory(ingredientName, app))
    val meals = viewModel.mealsWithIngredientState.value

    val showFilter = remember { mutableStateOf(false) }

    val isFavorite  by remember{ mutableStateOf( arrayOfNulls<Boolean>(meals.size))}

    Scaffold(topBar = {
        com.example.mealzapp.ui.utils.AppBar(
            "Meals with $ingredientName",
            icon = Icons.Default.ArrowBack,
            onClickNav,
            iconAction2 = Icons.Default.Search,
            onClickAction2 = { showFilter.value = !showFilter.value }
        )
    }) {

        Column {

            val findValue = remember { mutableStateOf(TextFieldValue()) }
            FindBar(
                findValue = findValue.value,
                onValueChange = { findValue.value = TextFieldValue(it) },
                onIconClick = {
                    findValue.value = TextFieldValue("")
                    showFilter.value = false
                },
                visible = showFilter.value
            )

            if (meals != null) {

                LazyColumn(contentPadding = PaddingValues(16.dp)) {

                    items(meals) { meal ->

                        if ((meal?.name ?: "").contains(
                                findValue.value.text,
                                ignoreCase = true
                            ) or (meal?.name ?: "" == "")
                        ) {
                            BarElement(
                                name = meal?.name ?: "",
                                description = "",
                                imageUrl = meal?.imageUrl ?: "" + "/preview",
                                elementId = meal?.id ?: "",
                                onClickNav = onClickBar,
                                iconRight = if (viewModel.isFavorite(meal?: MealResponse()))
                                    Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                onClickRight = {
                                    viewModel.togleToFavorites(it)
                                    onClickBarIcon.invoke(it)
                                               },

                                )
                        }

                    }

                }

            }
        }


    }

}

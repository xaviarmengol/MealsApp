package com.example.mealzapp.ui.mealWithCategory

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
fun MealsWithCategoryScreen(
    categoryName: String,
    onClickNav: () -> Unit,
    onClickBar: (String) -> Unit,
    onClickBarIcon: (MealResponse) -> Unit,
    app: Application
) {

    val viewModel: MealsWithCategoryViewModel =
        viewModel(factory = MealsWithCategoryViewModelFactory(categoryName, app))
    val meals = viewModel.mealsWithCategoryState.value

    val showFilter = remember { mutableStateOf(false) }

    Scaffold(topBar = {

        com.example.mealzapp.ui.utils.AppBar(
            "Meals with $categoryName",
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


            LazyColumn(contentPadding = PaddingValues(16.dp)) {

                if (meals != null) {

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
                                iconRight = if (viewModel.isFavorite(meal?:MealResponse()))
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

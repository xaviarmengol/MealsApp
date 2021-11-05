package com.example.mealzapp.ui.tabFilter

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.ingredients.IngredientsContentScreen
import com.example.mealzapp.ui.mealsCategories.MealsCategoriesContentScreen
import com.example.mealzapp.ui.mealsFavorites.MealsFavoritesContentScreen
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.BottomBar
import com.example.mealzapp.ui.utils.FindBar
import com.example.mealzapp.ui.utils.hasNetwork
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

//https://github.com/johncodeos-blog/TabsComposeExample/blob/main/app/src/main/java/com/example/tabscomposeexample/MainActivity.kt

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable

fun TabFilterScreen(onClickElementList: List<(String) -> Unit>, onClickNav: () -> Unit, app: Application) {

    val viewModel: TabFilterViewModel = viewModel(factory = TabFilterViewModelFactory(app))
    val hasConection = viewModel.hasConection.value

    val showFilter = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(0)

    Scaffold(
        topBar = {
            AppBar(
                title = "Find Meal",
                icon = Icons.Default.ArrowBack,
                onClickNav,
                iconAction1 = Icons.Default.Home,
                onClickAction1 = { },
                iconAction2 = Icons.Default.Search,
                onClickAction2 = { showFilter.value = !showFilter.value }
            )
        },
        backgroundColor = MaterialTheme.colors.background,

        bottomBar = {
            BottomBar(
                icon1 = if (hasConection) Icons.Default.Favorite else Icons.Default.KeyboardArrowDown,
                onClick1 = {viewModel.refreshConectionState()},
                icon2 = Icons.Default.ShoppingCart
            )
        }

    ) {

        val scope = rememberCoroutineScope()
        // OR ScrollableTabRow()
        Column {

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                }) {

                //LeadingIconTab
                Tab(
                    //icon = { Icon(Icons.Default.Star, contentDescription = "") },
                    text = { Text("Ingredients") },
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                )

                Tab(
                    //icon = { Icon(Icons.Default.Star, contentDescription = "") },
                    text = { Text("Categories") },
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                )

                Tab(
                    //icon = { Icon(Icons.Default.Star, contentDescription = "") },
                    text = { Text("Favorites") },
                    selected = pagerState.currentPage == 2,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                    },
                )
            }

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

            HorizontalPager(
                state = pagerState,
                count = 3,
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
                verticalAlignment = Alignment.Top

            ) { page ->

                when (page) {
                    0 -> IngredientsContentScreen(
                        onClickIngredient = onClickElementList[0],
                        filterValue = findValue.value.text,
                        app = app

                    )
                    1 -> MealsCategoriesContentScreen(
                        onClickCategory = onClickElementList[1],
                        filterValue = findValue.value.text,
                        app = app
                    )
                    2 -> MealsFavoritesContentScreen(
                        onClickMeal = onClickElementList[2],
                        filterValue = findValue.value.text
                    )
                }

            }
        }


    }
}


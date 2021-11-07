package com.example.mealzapp.ui.navigation

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.ui.ingredientsCart.IngredientsCartScreen
import com.example.mealzapp.ui.mealRecipe.MealRecipeScreen
import com.example.mealzapp.ui.mealWithCategory.MealsWithCategoryScreen
import com.example.mealzapp.ui.mealsWithIngredient.MealsWithIngredientsScreen
import com.example.mealzapp.ui.menus.MenusScreen
import com.example.mealzapp.ui.tabFilter.TabFilterScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalPagerApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@Composable

fun NavigationScreen(app: Application) {

    val navController = rememberNavController()
    val viewModel: NavigationViewModel = viewModel()

    NavHost(navController = navController, startDestination = "MENUS") {

        composable(route = "MENUS") {
            MenusScreen(
                onClickMenu = { navController.navigate("TAB_FILTER") },
                onClickMenuFilled = { mealId -> navController.navigate("MEAL_RECIPE/${mealId}") },
                onClickNav = {},
                onClickCart = { navController.navigate("INGREDIENTS_CART") },
                onLongClickMenuFilled= {
                    navController.navigate("MENUS")
                                       },
                app = app
            )
        }


        composable(route="TAB_FILTER") {

            val onClickElementList : List<(String) -> Unit> = listOf(
                {ingredientName -> navController.navigate("MEALS_WITH_INGREDIENT/${ingredientName}")},
                {categoryName -> navController.navigate("MEALS_WITH_CATEGORY/${categoryName}") },
                {mealId -> navController.navigate("MEAL_RECIPE/${mealId}") }
            )

            TabFilterScreen(onClickElementList, onClickNav = {navController.navigateUp()}, app)
        }


        composable(
            route = "MEALS_WITH_INGREDIENT/{ingredient_name}",
            arguments = listOf(navArgument("ingredient_name") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val ingredientName = navBackStackEntry.arguments?.getString("ingredient_name") ?: ""
            MealsWithIngredientsScreen(
                ingredientName = ingredientName,
                onClickNav = { navController.navigateUp() },
                onClickBar = { mealId -> navController.navigate("MEAL_RECIPE/${mealId}") },
                onClickBarIcon = { mealId ->
                    navController.navigate("MEALS_WITH_INGREDIENT/${ingredientName}")
                },
                app = app
            )
        }

        composable(
            route = "MEALS_WITH_CATEGORY/{category_name}",
            arguments = listOf(navArgument("category_name") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val categoryName = navBackStackEntry.arguments?.getString("category_name") ?: ""
            MealsWithCategoryScreen(
                categoryName = categoryName,
                onClickBar = { mealId -> navController.navigate("MEAL_RECIPE/${mealId}") },
                onClickNav = { navController.navigateUp() },
                onClickBarIcon = { mealId ->
                    navController.navigate("MEALS_WITH_CATEGORY/${categoryName}")
                },
                app = app
                )
        }


        composable(route = "MEAL_RECIPE/{meal_id}", arguments = listOf(navArgument("meal_id") {
            type = NavType.StringType
        })) { navBackStackEntry ->
            val mealId = navBackStackEntry.arguments?.getString("meal_id") ?: ""
            MealRecipeScreen(
                mealId = mealId,
                onClickNav = { navController.navigateUp() },
                onClickActionBar = {
                    viewModel.addMealToCurrentMenu(it)
                    navController.navigate("MENUS")
                },
                app = app
            )
        }

        composable(route = "INGREDIENTS_CART") {
            IngredientsCartScreen(
                onClickIngredientCart = { }, // TODO: navigate back to the meal
                onClickNav = { navController.navigateUp() }
            )


        }


    }
}


package com.example.model

import com.example.mealzapp.ui.utils.SingletonHolder
import com.example.model.api.MealsCachedWebService
import com.example.model.response.MealRecipeArrangedResponse
import com.example.model.response.MealResponse
import com.example.model.response.convertToRecipeArranged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoritesMealsDBRepository(private val webService: MealsCachedWebService) {

    val meals: MutableList<MealResponse> = mutableListOf()

    // TODO: Call repository from another repository. Is it right?
    private val repositoryRecipe: MealRecipesCachedRepository =
        MealRecipesCachedRepository.getInstance(webService)

    // TODO: It will be the DB later on
    private var mealFavoritesDB: MutableList<MealRecipeArrangedResponse> = mutableListOf()

    init {
        runBlocking {
            launch(Dispatchers.IO) {
                for (mealDB in mealFavoritesDB) {
                    val meal : MealResponse = MealResponse(mealDB.idMeal,mealDB.strMeal,mealDB.strMealThumb)
                    meals.add(meal)
                }
            }
        }
    }

    fun togle(meal: MealResponse) {
        if (contains(meal)) {
            remove(meal)
        } else {
            add(meal)
        }
    }

    fun add(meal: MealResponse) {
        runBlocking {
            launch(Dispatchers.IO) {
                meals.add(meal)
            }
            launch(Dispatchers.IO) {
                mealFavoritesDB.add(
                    convertToRecipeArranged(
                        repositoryRecipe.getMealRecipes(
                            meal.id ?: ""
                        ).recipes
                    )
                )
            }
        }
    }


    fun add(mealRecipe: MealRecipeArrangedResponse) {
        runBlocking {
            launch(Dispatchers.IO) {
                meals.add(MealResponse(mealRecipe.idMeal,mealRecipe.strMeal, mealRecipe.strImageSource))
            }
            launch(Dispatchers.IO) {
                mealFavoritesDB.add(mealRecipe)
            }
        }
    }

    fun getMealsFromDB() : List<MealRecipeArrangedResponse> {

        var mealRecipesReturn = listOf(MealRecipeArrangedResponse())

        runBlocking {
            launch(Dispatchers.IO) {

                mealRecipesReturn = mealFavoritesDB

            }
        }
        return mealRecipesReturn
    }

    fun remove(meal: MealResponse) {

        runBlocking {
            launch(Dispatchers.IO) {
                meals.remove(meal)
            }

            launch(Dispatchers.IO) {

                val mealToRemove = mealFavoritesDB.firstOrNull {
                        mealDB -> mealDB.idMeal == meal.id
                }
                mealFavoritesDB.remove(mealToRemove)

            }
        }
    }

    fun contains(meal: MealResponse): Boolean {
        //Log.i("RepositoryFav", "contains: ${meal in meals}")
        return meal in meals
    }

    // Convert into singleton
    companion object :
        SingletonHolder<FavoritesMealsDBRepository, MealsCachedWebService>(::FavoritesMealsDBRepository)
}
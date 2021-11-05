package com.example.model.api

import android.content.Context
import com.example.mealzapp.ui.utils.hasNetwork
import com.example.model.response.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.io.File

// https://stackoverflow.com/questions/23429046/can-retrofit-with-okhttp-use-cache-data-when-offline


class MealsDBCachedWebService (context: Context){

    private var apiMealsCategories: MealsCategoriesApi
    private var apiIngredients: IngredientsApi
    private var apiMealsWithIngredient : MealsWithIngredientsApi
    private var apiMealsWithCategory : MealsWithCategoryApi
    private var apiMealRecipes : MealRecipesApi

    private val MIN_TIME_BEFORE_CONECTING_AGAIN : Int = 60 * 5
    private val EXPIRE_TIME_CACHE_SEG : Int = 60 * 60 * 24 * 7


    init {


        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context))
                    request.newBuilder().header("Cache-Control", "public, max-age=${MIN_TIME_BEFORE_CONECTING_AGAIN}").build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=$EXPIRE_TIME_CACHE_SEG").build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        apiMealsCategories = retrofit.create(MealsCategoriesApi::class.java)
        apiIngredients = retrofit.create(IngredientsApi::class.java)
        apiMealsWithIngredient = retrofit.create(MealsWithIngredientsApi::class.java)
        apiMealsWithCategory = retrofit.create(MealsWithCategoryApi::class.java)
        apiMealRecipes = retrofit.create(MealRecipesApi::class.java)
    }

    // ----- Meals Categories

    suspend fun getMealsCategories(): CategoriesResponse {
        return apiMealsCategories.getMealsCategories()
    }

    interface MealsCategoriesApi {
        @GET("categories.php")
        suspend fun getMealsCategories(): CategoriesResponse
    }

    //----- Ingredients Categories

    suspend fun getIngredients(): IngredientsResponse {
        return apiIngredients.getIngredients()
    }

    interface IngredientsApi {
        @GET("list.php?i=list")
        suspend fun getIngredients(): IngredientsResponse
    }

    //----- Meals filtered by ingredient

    suspend fun getMealsWithIngredient(ingredientName : String): MealsResponse {
        return apiMealsWithIngredient.getMealsWithIngredient(ingredientName)
    }

    interface MealsWithIngredientsApi {
        @GET("filter.php")
        suspend fun getMealsWithIngredient(@Query("i") ingredientName : String ): MealsResponse
    }

    //----- Meals filtered by category

    suspend fun getMealsWithCategory(categoryName : String): MealsResponse {
        return apiMealsWithCategory.getMealsWithCategory(categoryName)
    }

    interface MealsWithCategoryApi {
        @GET("filter.php")
        suspend fun getMealsWithCategory(@Query("c") categoryName : String ): MealsResponse
    }


    // ---- Meal Recipes

    suspend fun getMealRecipes(mealId : String): MealRecipesResponse {
        return apiMealRecipes.getMealsWithIngredient(mealId)
    }

    interface MealRecipesApi {
        @GET("lookup.php")
        suspend fun getMealsWithIngredient(@Query("i") mealId : String ): MealRecipesResponse
    }


}
package com.example.model.response

import com.google.gson.annotations.SerializedName

// --- Meals Categories

// Not necessary to use @serializedNames because the name is the same that is in the API
data class CategoriesResponse(val categories:List<CategoryResponse> = listOf(CategoryResponse()))

data class CategoryResponse(
    @SerializedName("idCategory")  val id: String = "",
    @SerializedName("strCategory")  val name: String = "",
    @SerializedName("strCategoryDescription")  val description: String = "",
    @SerializedName("strCategoryThumb")  val imageUrl: String = ""
)

// --- Ingredients Categories

data class IngredientsResponse(@SerializedName("meals") val ingredients:List<IngredientResponse> = listOf())

data class IngredientResponse(
    @SerializedName("idIngredient")  val id: String?,
    @SerializedName("strIngredient")  val name: String?,
    @SerializedName("strDescription")  val description: String?,
    @SerializedName("strType")  val type: String?
)

// --- Meals from a filter (ingredient, category, area, ...)

data class MealsResponse(@SerializedName("meals") val meals:List<MealResponse?>)

data class MealResponse(
    @SerializedName("idMeal")  val id: String? = "",
    @SerializedName("strMeal")  val name: String? = "",
    @SerializedName("strMealThumb")  val imageUrl: String? ="",
)

// --- Meal Recipe

data class MealRecipesResponse(@SerializedName("meals") val recipes : List<MealRecipeResponse> = listOf(MealRecipeResponse()))

data class MealRecipeResponse(
    val dateModified : String="",
    val idMeal : String="",
    val strArea : String="",
    val strCategory : String="",
    val strCreativeCommonsConfirmed : String="",
    val strDrinkAlternate : String="",
    val strImageSource : String="",
    val strIngredient1 : String="",
    val strIngredient10 : String="",
    val strIngredient11 : String="",
    val strIngredient12 : String="",
    val strIngredient13 : String="",
    val strIngredient14 : String="",
    val strIngredient15 : String="",
    val strIngredient16 : String="",
    val strIngredient17 : String="",
    val strIngredient18 : String="",
    val strIngredient19 : String="",
    val strIngredient2 : String="",
    val strIngredient20 : String="",
    val strIngredient3 : String="",
    val strIngredient4 : String="",
    val strIngredient5 : String="",
    val strIngredient6 : String="",
    val strIngredient7 : String="",
    val strIngredient8 : String="",
    val strIngredient9 : String="",
    val strInstructions : String="",
    val strMeal : String="",
    val strMealThumb : String="",
    val strMeasure1 : String="",
    val strMeasure10 : String="",
    val strMeasure11 : String="",
    val strMeasure12 : String="",
    val strMeasure13 : String="",
    val strMeasure14 : String="",
    val strMeasure15 : String="",
    val strMeasure16 : String="",
    val strMeasure17 : String="",
    val strMeasure18 : String="",
    val strMeasure19 : String="",
    val strMeasure2 : String="",
    val strMeasure20 : String="",
    val strMeasure3 : String="",
    val strMeasure4 : String="",
    val strMeasure5 : String="",
    val strMeasure6 : String="",
    val strMeasure7 : String="",
    val strMeasure8 : String="",
    val strMeasure9 : String="",
    val strSource : String="",
    val strTags : String="",
    val strYoutube : String="",
)


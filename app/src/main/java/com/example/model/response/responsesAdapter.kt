package com.example.model.response


data class IngredientNameMeasure (var name: String?, var measure: String?)
data class IngredientMealImageNameMeasure (var mealImage: String?, var name: String?, var measure: String?)

data class MealRecipeArrangedResponse constructor(
    val dateModified : String? = "",
    val idMeal : String?= "",
    val strArea : String?= "",
    val strCategory : String?= "",
    val strCreativeCommonsConfirmed : String?= "",
    val strDrinkAlternate : String?= "",
    val strImageSource : String?= "",
    val strInstructions : String?= "",
    val strMeal : String?= "",
    val strMealThumb : String?= "",
    val strSource : String?= "",
    val strTags : String?= "",
    val strYoutube : String?= "",
    val ingredientsRecipe : List <IngredientNameMeasure> =
        List (20) {IngredientNameMeasure("", "")},

)


fun convertToRecipesArranged(recipes : List<MealRecipeResponse>): MealRecipeArrangedResponse {

    val ingredientsRecipe : List<IngredientNameMeasure> =
        List<IngredientNameMeasure> (20) { IngredientNameMeasure("", "") }

    fun initIngredientsRecipe() {

        ingredientsRecipe[0].name = recipes[0].strIngredient1
        ingredientsRecipe[1].name = recipes[0].strIngredient2
        ingredientsRecipe[2].name = recipes[0].strIngredient3
        ingredientsRecipe[3].name = recipes[0].strIngredient4
        ingredientsRecipe[4].name = recipes[0].strIngredient5
        ingredientsRecipe[5].name = recipes[0].strIngredient6
        ingredientsRecipe[6].name = recipes[0].strIngredient7
        ingredientsRecipe[7].name = recipes[0].strIngredient8
        ingredientsRecipe[8].name = recipes[0].strIngredient9
        ingredientsRecipe[9].name = recipes[0].strIngredient10
        ingredientsRecipe[10].name = recipes[0].strIngredient11
        ingredientsRecipe[11].name = recipes[0].strIngredient12
        ingredientsRecipe[12].name = recipes[0].strIngredient13
        ingredientsRecipe[13].name = recipes[0].strIngredient14
        ingredientsRecipe[14].name = recipes[0].strIngredient15
        ingredientsRecipe[15].name = recipes[0].strIngredient16
        ingredientsRecipe[16].name = recipes[0].strIngredient17
        ingredientsRecipe[17].name = recipes[0].strIngredient18
        ingredientsRecipe[18].name = recipes[0].strIngredient19
        ingredientsRecipe[19].name = recipes[0].strIngredient20
        ingredientsRecipe[0].measure = recipes[0].strMeasure1
        ingredientsRecipe[1].measure = recipes[0].strMeasure2
        ingredientsRecipe[2].measure = recipes[0].strMeasure3
        ingredientsRecipe[3].measure = recipes[0].strMeasure4
        ingredientsRecipe[4].measure = recipes[0].strMeasure5
        ingredientsRecipe[5].measure = recipes[0].strMeasure6
        ingredientsRecipe[6].measure = recipes[0].strMeasure7
        ingredientsRecipe[7].measure = recipes[0].strMeasure8
        ingredientsRecipe[8].measure = recipes[0].strMeasure9
        ingredientsRecipe[9].measure = recipes[0].strMeasure10
        ingredientsRecipe[10].measure = recipes[0].strMeasure11
        ingredientsRecipe[11].measure = recipes[0].strMeasure12
        ingredientsRecipe[12].measure = recipes[0].strMeasure13
        ingredientsRecipe[13].measure = recipes[0].strMeasure14
        ingredientsRecipe[14].measure = recipes[0].strMeasure15
        ingredientsRecipe[15].measure = recipes[0].strMeasure16
        ingredientsRecipe[16].measure = recipes[0].strMeasure17
        ingredientsRecipe[17].measure = recipes[0].strMeasure18
        ingredientsRecipe[18].measure = recipes[0].strMeasure19
        ingredientsRecipe[19].measure = recipes[0].strMeasure20
    }

    initIngredientsRecipe()

    return MealRecipeArrangedResponse (
        dateModified = recipes[0].dateModified,
        idMeal = recipes[0].idMeal,
        strArea = recipes[0].strArea,
        strCategory = recipes[0].strCategory,
        strCreativeCommonsConfirmed = recipes[0].strCreativeCommonsConfirmed,
        strDrinkAlternate = recipes[0].strDrinkAlternate,
        strImageSource = recipes[0].strImageSource,
        strInstructions = recipes[0].strInstructions,
        strMeal = recipes[0].strMeal,
        strMealThumb = recipes[0].strMealThumb,
        strSource = recipes[0].strSource,
        strTags = recipes[0].strTags,
        strYoutube = recipes[0].strYoutube,
        ingredientsRecipe = ingredientsRecipe
    )
}


package com.example.mealzapp.ui.mealRecipe

import android.app.Application
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.ImageCategory
import com.example.model.response.IngredientNameMeasure
import com.example.model.response.MealRecipeArrangedResponse

@Composable
fun MealRecipeScreen(mealId: String, onClickNav: () -> Unit, onClickActionBar: (MealRecipeArrangedResponse) -> Unit, app : Application) {

    val viewModel: MealRecipeCachedViewModel = viewModel(factory = MealRecipesCachedViewModelFactory(mealId, app))

    val recipeArranged = viewModel.mealsRecipeArrangedState.value
    val titleScreen = viewModel.titleScreen.value
    val recipeStepByStep = viewModel.recipeSteps.value

    val isFavorite = viewModel.isFavorite.value

    val scroll: ScrollState by remember { mutableStateOf(ScrollState(0)) }

    Scaffold(topBar = {
        AppBar(
            "$titleScreen",
            icon = Icons.Default.ArrowBack,
            onClick = onClickNav,
            iconAction1 = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            onClickAction1 = { viewModel.togle() },
            iconAction2 = Icons.Default.Add,
            onClickAction2 = {onClickActionBar(recipeArranged)}
        )
    })
    {
        //Text(ingredientsRecipe[0].measure)

        if (recipeArranged.idMeal != null) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scroll),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    recipeArranged.strMeal ?: "",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.h5
                )
                ImageCategory(imageUrl = recipeArranged.strMealThumb ?: "", size = 260.dp)

                Spacer(Modifier.size(16.dp))

                RecipeIngredientsSelector(
                    ingredientsList = recipeArranged.ingredientsRecipe,
                    mealName = recipeArranged.strMealThumb?:"",
                    onClickIngredient = {mealName, ingredient, measure ->
                        viewModel.togleIngredient(mealName, ingredient, measure) },
                    isInCart = { mealName, ingredient, measure ->
                        viewModel.ingredientIsInCart(mealName, ingredient, measure) },
                )

                Spacer(Modifier.size(16.dp))

                RecipeSteps(recipeStepByStep)

                Spacer(Modifier.size(16.dp))

                Text(
                    "Source: ${recipeArranged.strSource ?: ""}, Image: ${recipeArranged.strImageSource ?: ""}",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption
                )

            }
        }

    }
}

@Composable
fun RecipeSteps(steps: List<String>) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        var numStepReal = 0

        for (numStep in 0 until steps.size) {
            val stepDescription = steps[numStep]
            val cleanStep = stepDescription.replace(" ", "").replace("\r", "")

            if (cleanStep != "") {
                numStepReal++
                RecipeStep(numStepReal, stepDescription)
            }
        }

    }

}

@Composable
fun RecipeStep(num: Int, stepDescription: String) {

    val checked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { checked.value = !checked.value },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,

        )
    {
        Text(
            text = "$num",
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Justify
        )

        CompositionLocalProvider(
            LocalContentAlpha provides
                    if (!checked.value) ContentAlpha.high else ContentAlpha.disabled
        ) {
            Text(
                text = stepDescription,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(8f),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify
            )
        }

    }


}


@Composable
fun RecipeIngredientsSelector(
    ingredientsList: List<IngredientNameMeasure>,
    mealName: String,
    onClickIngredient: (String, String, String) -> Unit,
    isInCart : (String, String, String) -> Boolean,
) {

    ingredientsList.forEach { ingredient ->
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            if ((ingredient.name != null) and (ingredient.name != "")) {
                IngredientBar(
                    mealName = mealName,
                    name = ingredient.name ?: "",
                    measure = ingredient.measure ?: "",
                    onClickIngredient = onClickIngredient,
                    isInCart = isInCart,
                )
            }
        }
    }
}


@Composable
fun IngredientBar(
    mealName: String,
    name: String,
    measure: String,
    onClickIngredient: (String, String, String) -> Unit,
    isInCart : (String, String, String) -> Boolean,
) {

    var checked: Boolean by remember { mutableStateOf(false) }

    checked = isInCart(mealName, name, measure)

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )

        {

            Text(name, modifier = Modifier.weight(2f), textAlign = TextAlign.Justify)
            Text(measure, modifier = Modifier.weight(2f), textAlign = TextAlign.Justify)
            Checkbox(
                modifier = Modifier.weight(1f),
                checked = checked,
                onCheckedChange = {
                    onClickIngredient(mealName, name, measure)
                    checked = isInCart(mealName, name, measure)
                }
            )
        }
    }


}


package com.example.mealzapp.ui.utilsComposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.mealzapp.ui.utils.BarElement
import com.example.model.response.MealResponse

@Composable
fun BarElements  (
    elements : List<Any>? = null,
    name: String = "",
    description: String = "",
    imageUrl: String = "noImage",
    elementId: String = name,
    onClickNav: (String) -> Unit,
    iconRight: ImageVector = Icons.Rounded.Menu,
    onClickRight: ((MealResponse) -> Unit)? = null,
) {

    LazyColumn(contentPadding = PaddingValues(16.dp)) {

        if (elements != null) {
            items(elements) { element ->
                //Meal(meal, onClickMeal)
                BarElement(
                    name = name,
                    description = description,
                    imageUrl = imageUrl,
                    elementId = elementId,
                    onClickNav = onClickNav,
                    iconRight = iconRight,
                    onClickRight = onClickRight,

                    )
            }
        }

    }

}
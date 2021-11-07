package com.example.mealzapp.ui.menus

import android.app.Application
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.ui.utils.AppBar
import com.example.mealzapp.ui.utils.BarElementBig
import com.example.mealzapp.ui.utilsComposables.PlaceHolders
import com.example.mealzapp.ui.utilsComposables.PlusSign


@ExperimentalComposeUiApi
@Composable
fun MenusScreen(
    onClickMenu: () -> Unit,
    onClickMenuFilled: (String) -> Unit,
    onLongClickMenuFilled: () -> Unit,
    onClickNav: () -> Unit,
    onClickCart: () -> Unit,
    app: Application
) {
    // attach the viewmodel to the composable (Live as long as the composable)

    val viewModel: MenuViewModel = viewModel(factory = MenuViewModelFactory(app))

    val placeHolder: MutableList<@Composable () -> Unit> = mutableListOf({}, {}, {})

    //val placeHolderList : List<@Composable () -> Unit> = remember{listOf({}, {}, {})}

    val findValue = remember { mutableStateOf(TextFieldValue(viewModel.getMenuName())) }
    val keyboardController = LocalSoftwareKeyboardController.current

    for (mealNum in (0 until 3)) {
        if (viewModel.isMealFull(mealNum)) {
            placeHolder[mealNum] = {
                BarElementBig(
                    name = viewModel.activeMenuState.value.meals[mealNum].strMeal?:"",
                    area = viewModel.activeMenuState.value.meals[mealNum].strArea?:"",
                    category = viewModel.activeMenuState.value.meals[mealNum].strCategory?:"",
                    imageUrl = viewModel.activeMenuState.value.meals[mealNum].strMealThumb?:"",
                    onClickNav = {
                        viewModel.setMealActive(mealNum)
                        val idMeal = viewModel.activeMenuState.value.meals[mealNum].idMeal?:""
                        if (idMeal != "") onClickMenuFilled.invoke(idMeal)
                        Log.i("MenuScreen", "MenusScreen: IdMeal Nav: $idMeal")
                    },
                    onLongClickNav = {
                        viewModel.resetMeal(mealNum)
                        onLongClickMenuFilled.invoke()
                    }
                )
            }

        } else {
            placeHolder[mealNum] = {
                PlusSign {
                    viewModel.setMealActive(mealNum)
                    onClickMenu.invoke()
                }
            }
        }
    }

    Scaffold(topBar = {
        AppBar(
            "My Menu",
            icon = Icons.Default.Home,
            onClickNav,
            iconAction1 = Icons.Default.ShoppingCart,
            onClickAction1 = onClickCart

        )
    }) {
        Column {
            TextField(
                value = findValue.value.text,
                onValueChange = {
                    findValue.value = TextFieldValue(it)
                    viewModel.setMenuName(it)
                                },
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "close",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable(onClick = { findValue.value = TextFieldValue("") })
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                ),

                placeholder = { Text(text="Menu name", textAlign = TextAlign.Center) },
                //label =  { Text("filter by") }
            )

            PlaceHolders(placeHolder)
        }
    }

}
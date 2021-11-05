package com.example.mealzapp.ui.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun FindBar(findValue: TextFieldValue, onValueChange: (String) -> Unit, onIconClick: () -> Unit, visible: Boolean = true) {

    val keyboardController = LocalSoftwareKeyboardController.current
    AnimatedVisibility(visible) {
        TextField(
            value = findValue.text,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "close",
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable(onClick = onIconClick)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            placeholder = { Text("filter by") },
            //label =  { Text("filter by") }
        )
    }

}
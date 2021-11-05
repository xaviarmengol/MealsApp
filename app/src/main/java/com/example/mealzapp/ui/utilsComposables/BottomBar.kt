package com.example.mealzapp.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar  (
    icon1: ImageVector? = null,
    onClick1: () -> Unit = {},
    icon2: ImageVector? = null,
    onClick2: () -> Unit = {}
) {
    BottomAppBar(
        //contentPadding = PaddingValues(all = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                "Left Button",
                modifier = Modifier.clickable(onClick = {})
            )
            if (icon1!=null) {
                Icon(
                    icon1,
                    "Center 1 Button",
                    modifier = Modifier.clickable(onClick = onClick1)
                )
            }
            if (icon2!=null) {
                Icon(
                    icon2,
                    "Center 2 Button",
                    modifier = Modifier.clickable(onClick = onClick2)
                )
            }
            Icon(
                Icons.Default.KeyboardArrowRight,
                "Right Button",
                modifier = Modifier.clickable(onClick = {})
            )
        }

    }
}


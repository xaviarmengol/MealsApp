package com.example.mealzapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun AppBar(title: String,
           icon: ImageVector,
           onClick: () -> Unit,
           iconAction1: ImageVector? = null,
           onClickAction1: () -> Unit = {},
           iconAction2: ImageVector? = null,
           onClickAction2: () -> Unit = {},
) {
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = icon,"Icon Home",
                modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClick))
        },
        title = { Text(title) },
        actions = {
            if (iconAction1 != null)
            Icon(imageVector = iconAction1,"Icon Action 1",
                modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClickAction1))

            if (iconAction2 != null)
            Icon(imageVector = iconAction2,"Icon Action 2",
                modifier = Modifier.padding(horizontal = 12.dp).clickable(onClick = onClickAction2))
        }

    )
}



@Composable
fun ImageCategory(
    imageUrl: String, size: Dp, modifier: Modifier = Modifier
        .size(size)
        .padding(4.dp)
) {

    Card(
        shape = MaterialTheme.shapes.small,
        //elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = imageUrl,
            ),
            contentDescription = "Image meal",
            modifier = modifier
        )
    }

}


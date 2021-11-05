package com.example.mealzapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Composable
fun BarElementBig(
    name: String,
    area: String,
    category: String,
    imageUrl: String,
    onClickNav: (Offset) -> Unit = {},
    onLongClickNav: (Offset) -> Unit = {}
) {


    Surface(
        modifier = Modifier
            //.clickable(onClick = onClickNav)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = onLongClickNav,
                    onTap = onClickNav
                )
            }
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            elevation = 1.dp,
            shape = MaterialTheme.shapes.small,
            backgroundColor = MaterialTheme.colors.background
        )

        {

            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        //transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "Meal",
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(1f),
                alpha = 0.4f,
                contentScale = ContentScale.FillWidth
            )



            Row (
                modifier = Modifier.padding(3.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start){

                //Card (){

                //}


                Column(
                    modifier = Modifier.padding(4.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        name,
                        //color = Color.Black,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )

                    Text(
                        "$category",
                        //color = Color.Black,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )

                    Text(
                        "$area",
                        //color = Color.Black,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )


                }
            }
        }


    }


}
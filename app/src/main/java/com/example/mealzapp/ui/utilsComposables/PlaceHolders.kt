package com.example.mealzapp.ui.utilsComposables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlaceHolders(contents: List<@Composable () -> Unit>) {

    val totalBox = contents.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        for (numBox in (0 until totalBox)) {
            val divider = totalBox - numBox

            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f / divider.toFloat())
                    .padding(0.dp)
                    .border(1.dp,color=MaterialTheme.colors.onSurface)
                    //.swipeToDelete(offsetX[numBox], 1f){}
                    //.offset(x = offsetX[0].value.dp)
                ,
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) { contents[numBox].invoke()}
        }
    }
}


@Composable
fun PlusSign(onClick : () -> Unit) {
    val startPercent = 0.35f

    Canvas(modifier = Modifier.fillMaxSize().clickable(true,onClick = onClick)) {
        val canvasWidthCenter = size.width/2.0f
        val canvasHeightCenter = size.height/2.0f
        val pathDotted = PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f)
        val distanceCrossLeg = (kotlin.math.min(canvasHeightCenter,canvasWidthCenter)) * 0.5f


        drawLine(
            start = Offset(x = canvasWidthCenter, y = canvasHeightCenter - distanceCrossLeg),
            end = Offset(x = canvasWidthCenter, y = canvasHeightCenter + distanceCrossLeg),
            color = Color.LightGray,
            strokeWidth = 10f,
            pathEffect = pathDotted
        )

        drawLine(
            start = Offset(x = canvasWidthCenter - distanceCrossLeg, y = canvasHeightCenter),
            end = Offset(x = canvasWidthCenter + distanceCrossLeg, y = canvasHeightCenter),
            color = Color.LightGray,
            strokeWidth = 10f,
            pathEffect = pathDotted
        )

    }

}


@Preview
@Composable
fun PreviewMyRecipes() {
    PlaceHolders(listOf({ Text("aaaa") }, { PlusSign({}) }, { PlusSign({}) }, { Text("bbbb") }))
}
package com.example.mealzapp.ui.utils

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.model.response.MealResponse

@Composable
fun BarElement(
    name: String = "",
    description: String = "",
    imageUrl: String = "noImage",
    elementId: String = "",
    onClickNav: (String) -> Unit,
    iconRight: ImageVector = Icons.Rounded.Menu,
    onClickRight: ((MealResponse) -> Unit)? = null,

    ) {

    var expanded by remember { mutableStateOf(false)}

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {
                onClickNav(elementId)
            }
    ) {
        Row {
            if (imageUrl != "noImage") {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
                    .animateContentSize()
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h6
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (expanded) 10 else 2
                    )

                }

            }

            if (onClickRight == null)  {
                Icon(
                    if (!expanded) Icons.Rounded.Menu else Icons.Default.Clear,
                    contentDescription = "Expand Icon",
                    modifier = Modifier
                        .clickable {expanded = !expanded }
                        .align(
                            if (!expanded) Alignment.Top else Alignment.Bottom
                        )
                        .padding(4.dp)
                )
            } else  {
                Icon(
                    iconRight,
                    contentDescription = "Action Right",
                    modifier = Modifier
                        .clickable {onClickRight(MealResponse(elementId, name, imageUrl))}
                        .align(
                            if (!expanded) Alignment.Top else Alignment.Bottom
                        )
                        .padding(4.dp)
                )
            }


        }
    }

}


@Composable
fun BarElementDeletable(
    name: String = "",
    description: String = "",
    imageUrl: String = "noImage",
    elementId: String = name,
    onClickNav: (String) -> Unit,
    iconRight: ImageVector = Icons.Rounded.Menu,
    onClickRight: ((String) -> Unit)? = null,

    ) {

    var expanded by remember { mutableStateOf(false)}
    var strikethrough by remember { mutableStateOf(false)}

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                strikethrough = !strikethrough
                onClickNav(elementId)
            }
    ) {
        Row {
            if (imageUrl != "noImage") {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
                    .animateContentSize()
            ) {
                Text(
                    text = name,
                    style = if (!strikethrough) MaterialTheme.typography.h6 else TextStyle(textDecoration = TextDecoration.LineThrough)
                )

                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = description,
                        textAlign = TextAlign.Start,
                        style = if (!strikethrough) MaterialTheme.typography.subtitle2 else TextStyle(textDecoration = TextDecoration.LineThrough),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (expanded) 10 else 2
                    )

                }

            }

            if (onClickRight == null)  {
                Icon(
                    if (!expanded) Icons.Rounded.Menu else Icons.Default.Clear,
                    contentDescription = "Expand Icon",
                    modifier = Modifier
                        .clickable {expanded = !expanded }
                        .align(
                            if (!expanded) Alignment.Top else Alignment.Bottom
                        )
                        .padding(4.dp)
                )
            } else  {
                Icon(
                    iconRight,
                    contentDescription = "Action Right",
                    modifier = Modifier
                        .clickable {onClickRight(elementId)}
                        .align(
                            if (!expanded) Alignment.Top else Alignment.Bottom
                        )
                        .padding(4.dp)
                )
            }


        }
    }

}

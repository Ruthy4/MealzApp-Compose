package com.example.mealzapp.ui.meals.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.mealzapp.model.response.MealResponse

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")
    val imageSize: Dp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val imageColor by transition.animateColor(targetValueByState = { it.color }, label = "")
    val imageWidth by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

    Column() {
        Row() {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(width = imageWidth, color = imageColor)
            ) {
                Image(
                    painter = rememberImagePainter(data = meal?.imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .padding(16.dp)
                )
            }
            Text(
                text = meal?.name ?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Button(
            onClick = {
                profilePictureState =
                    if (profilePictureState == MealProfilePictureState.Normal)
                        MealProfilePictureState.Expanded
                    else MealProfilePictureState.Normal
            }, modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Change state of meal profile picture")
        }
    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}
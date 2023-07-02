package com.moaazelneshawy.myapplication

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
*
* like tween(), spring(), we have keyFrames{}
* with KeyFrames{}, we can specify at what point what we need to do
*
* https://www.youtube.com/watch?v=trVmP1rw0uw&list=PLQkwcJG4YTCSpJ2NLhDTHhi6XBNfk9WiC&index=11
* */

@Composable
fun AnimatedBox() {

    var sizeState by remember {
        mutableStateOf(150.dp)
    }
    val boxSize by animateDpAsState(
        targetValue = sizeState,
        spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )

    /*
    * This will work only one time for the background or changed on action like the click
    *
    var bg by remember {
        mutableStateOf(Color.Yellow)
    }
    val boxBackground by animateColorAsState(
        targetValue = bg,
        tween(easing = LinearOutSlowInEasing, delayMillis = 1000, durationMillis = 2000)
    )
    *
    * but if we need an animation that keep working by it self we can use infiniteTransition
    * */

    val infiniteColor = rememberInfiniteTransition()
    val boxBackground by infiniteColor.animateColor(
        initialValue = Color.Yellow,
        targetValue = Color.DarkGray,
        animationSpec = infiniteRepeatable(tween(500), repeatMode = RepeatMode.Reverse)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {


        Card(shape = RoundedCornerShape(200f)) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(boxBackground)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        var box2Color by remember {
            mutableStateOf(Color.Red)
        }
        val box2Background by animateColorAsState(
            targetValue = box2Color,
            tween(easing = LinearOutSlowInEasing, delayMillis = 1000, durationMillis = 2000)
        )
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(box2Background),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                sizeState += 50.dp
                box2Color = Color.DarkGray
            }) {
                Text(text = "Click")
            }
        }

    }
}
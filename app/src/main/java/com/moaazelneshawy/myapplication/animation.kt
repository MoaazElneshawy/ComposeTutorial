package com.moaazelneshawy.myapplication

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
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
        initialValue = Color.Magenta,
        targetValue = Color.DarkGray,
        animationSpec = infiniteRepeatable(tween(500), repeatMode = RepeatMode.Reverse)
    )

    Box(
        modifier = Modifier
            .size(boxSize)
            .background(boxBackground),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState += 50.dp
//            bg = Color.DarkGray
        }) {
            Text(text = "Click")
        }
    }
}
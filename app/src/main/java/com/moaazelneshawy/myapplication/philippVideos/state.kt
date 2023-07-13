package com.moaazelneshawy.myapplication.philippVideos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun ColorBox(modifier: Modifier, updateColor: (Color) -> Unit) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Blue)
        .clickable {
            val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
            updateColor.invoke(color)
        })
}

@Composable
fun CreateColorsColumn() {
    /*
     remember : means that we set it only first time
                and will not be reset every time the compose view recreated

     mutableStateOf : contains a live state like LiveData
    * */
    val boxColor = remember {
        mutableStateOf(Color.Yellow)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ColorBox(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            updateColor = {
                boxColor.value = it
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(boxColor.value)
        )

    }

}
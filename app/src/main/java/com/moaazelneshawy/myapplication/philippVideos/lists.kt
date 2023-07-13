package com.moaazelneshawy.myapplication.philippVideos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    for creating list we can use
    1- Column/Row and assign scroll state to it
    2- LazyColumn/LazyRow , which are scrollable by default

    LazyColumns are better than normal columns because they create items only when scrolling
    but Columns create the whole list when the app starts.
* */

@Composable
fun NormalColumn() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..50) {
            Text(
                text = "Item $i",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun LazyColumnsExample() {
    val stringsList = listOf("My", "Name", "is", "Moaaz")
    LazyColumn(content = {
        itemsIndexed(stringsList) { index, item ->
            Text(
                text = "$index- $item",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }


        /*  items(50) {
              Text(
                  text = "Item $it",
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(10.dp),
                  fontWeight = FontWeight.Bold,
                  fontSize = 13.sp
              )
          }*/
    })
}
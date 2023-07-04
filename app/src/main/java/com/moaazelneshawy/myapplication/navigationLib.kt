package com.moaazelneshawy.myapplication

import android.os.Parcelable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.myapplication.destinations.InfoScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

/*
* This library makes navigation mush easier that google team Navigation
* */

@Parcelize
data class User(
    val name: String = "Moaaz",
    val age: Int = 30
) : Parcelable

@Composable
fun CustomNavLibExample() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(navigator: DestinationsNavigator) {

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            scope.launch {
                navigator.navigate(InfoScreenDestination(User()))
            }
        }) {
            Text(text = "Show Info")
        }
    }
}

@Destination
@Composable
fun InfoScreen(user: User) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$user", fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}
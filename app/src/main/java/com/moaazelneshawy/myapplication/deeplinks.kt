package com.moaazelneshawy.myapplication

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

/*
* we can read deepLinks from Navigation
* */

@Composable
fun DeepLinkExample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = { navController.navigate("data") }) {
                    Text(text = "Go To Data")
                }
            }
        }
        composable(
            "data",
            deepLinks = listOf(
                /*
                * we use navDeepLinks
                * assign the uriPattern with the coming data
                * and the action
                * */
                navDeepLink {
                    uriPattern = "https://****/{id}"
                    action = Intent.ACTION_VIEW
                }),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id")
            // now we can use this
            Log.e("TAG", "DeepLinkExample: $id")
        }
    }
}
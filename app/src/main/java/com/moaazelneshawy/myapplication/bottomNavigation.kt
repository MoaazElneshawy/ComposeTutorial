package com.moaazelneshawy.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

data class BottomNavItem(
    val title: String,
    val route: String,
    val image: ImageVector,
    val badgeCount: Int = 0
)

const val HOME = "home"
const val NOTIFICATIONS = "notifications"
const val SETTINGS = "settings"

@Composable
fun BottomNavBarExample() {

    /*
    * We need Scaffold to show the bottom bar stacked to Bottom of the screen
    * also Scaffold used with Material design views like drawers , toolbars and so on.
    * */

    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem("Home", HOME, Icons.Default.Home),
                    BottomNavItem("Notifications", NOTIFICATIONS, Icons.Default.Notifications, 50),
                    BottomNavItem("Settings", SETTINGS, Icons.Default.Settings),
                ),
                navController = navHostController,
                onItemClicked = { navHostController.navigate(it.route) }
            )
        },
        content = {
            NavigationScreens(it, navController = navHostController)
        }
    )
}

@Composable
fun NavigationScreens(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Home")
            }
        }
        composable(NOTIFICATIONS) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Notifications")
            }
        }
        composable(SETTINGS) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Settings")
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClicked: (item: BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        backgroundColor = Color.DarkGray
    ) {
        /*
              * we need keep tracking the current destination change from NavController,
              * so we will collect it as state
              * */
        val backStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected, onClick = { onItemClicked(item) },
                icon = {
                    if (item.badgeCount > 0) {
                        BadgedBox(
                            badge = {
                                Text(
                                    text = "${item.badgeCount}",
                                    modifier = Modifier
                                        .background(
                                            Color.Red,
                                            shape = CircleShape
                                        )
                                        .padding(1.dp)
                                )
                            }
                        ) {
                            Icon(imageVector = item.image, contentDescription = item.title)
                        }
                    } else {
                        Icon(imageVector = item.image, contentDescription = item.title)
                    }

                },
                alwaysShowLabel = false,
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray
            )
        }
    }
}
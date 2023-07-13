package com.moaazelneshawy.myapplication.philippVideos

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.moaazelneshawy.myapplication.R
import kotlinx.coroutines.launch


@Composable
fun MyDrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Moaaz")
    }
}

data class DrawerItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MyDrawerItems(items: List<DrawerItem>, onItemClicked: (DrawerItem) -> Unit) {

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        items(items.size) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .clickable {
                        onItemClicked.invoke(items[it])
                    }) {
                Icon(imageVector = items[it].icon, contentDescription = items[it].title)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = items[it].title)
            }
        }
    }

}


@Composable
fun MyAppBar(onNavigationIconClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClicked) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerExample() {
    val scaffoldState = rememberScaffoldState()

    var menuTitle by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MyAppBar {
                scope.launch {
                    if (scaffoldState.drawerState.isClosed)
                        scaffoldState.drawerState.open()
                }
            }
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawerHeader()
            MyDrawerItems(items =
            listOf(
                DrawerItem("Home", Icons.Default.Home),
                DrawerItem("Settings", Icons.Default.Settings),
                DrawerItem("Help", Icons.Default.Warning),
            ), onItemClicked = {
                scope.launch { scaffoldState.drawerState.close() }
                menuTitle = it.title
            })
        },
        /*
        * drawerGesturesEnabled detects if gestures are enabled or not
        * is better to enable it only if drawer is open if we use topBar so user can close it by swiping
        * as drawer can be open with any gesture event
        * */
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = menuTitle)
        }
    }

}
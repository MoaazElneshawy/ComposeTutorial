package com.moaazelneshawy.myapplication.androidTutorials

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moaazelneshawy.myapplication.R
import com.moaazelneshawy.myapplication.ui.theme.spacing

data class Element(
    @StringRes val title: Int, @DrawableRes val imgRes: Int
)


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RealisticApp() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface),
        bottomBar = {
            RealisticAppBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
            )
        },
        content = {
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.padding(10.dp)) {
                SearchBar()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                AlignBodySection()
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                FavoritesSection()
            }
        })
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var textValue by remember {
        mutableStateOf("")
    }
    TextField(value = textValue,
        onValueChange = { textValue = it },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp),
        placeholder = { Text(text = stringResource(id = R.string.placeholder_search)) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search") })
}

@Composable
fun AlignBodySection() {
    Text(
        text = stringResource(id = R.string.align_your_body).uppercase(),
        style = MaterialTheme.typography.h2,
        fontSize = 16.sp
    )
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    val bodyElements = listOf<Element>(
        Element(R.string.ab1_inversions, R.drawable.ab1_inversions),
        Element(R.string.ab2_quick_yoga, R.drawable.ab2_quick_yoga),
        Element(R.string.ab3_stretching, R.drawable.ab3_stretching),
        Element(R.string.ab4_tabata, R.drawable.ab4_tabata),
        Element(R.string.ab5_hiit, R.drawable.ab5_hiit),
        Element(R.string.ab6_pre_natal_yoga, R.drawable.ab6_pre_natal_yoga),
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(15.dp),
        content = {
            items(bodyElements) {
                AlignBodyElement(it.title, it.imgRes)
            }
        })

}

@Composable
fun AlignBodyElement(@StringRes title: Int, @DrawableRes imgRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = MaterialTheme.spacing.medium)
    ) {
        Image(
            painter = painterResource(id = imgRes),
            contentDescription = stringResource(id = title),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = stringResource(id = title),
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
        )
    }
}


@Composable
fun FavoritesSection() {
    Text(
        text = stringResource(id = R.string.favorite_collections).uppercase(),
        style = MaterialTheme.typography.h1,
        fontSize = 16.sp
    )
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    val favoriteCollections = listOf<Element>(
        Element(R.string.fc1_short_mantras, R.drawable.fc1_short_mantras),
        Element(R.string.fc2_nature_meditations, R.drawable.fc2_nature_meditations),
        Element(R.string.fc3_stress_and_anxiety, R.drawable.fc3_stress_and_anxiety),
        Element(R.string.fc4_self_massage, R.drawable.fc4_self_massage),
        Element(R.string.fc5_overwhelmed, R.drawable.fc5_overwhelmed),
        Element(R.string.fc6_nightly_wind_down, R.drawable.fc6_nightly_wind_down),
    )

    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier.height(120.dp),
        content = {
            items(favoriteCollections) {
                FavoriteCollectionElement(title = it.title, imgRes = it.imgRes)
            }
        })

}

@Composable
fun FavoriteCollectionElement(@StringRes title: Int, @DrawableRes imgRes: Int) {
    Surface(
        shape = MaterialTheme.shapes.small, modifier = Modifier.height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(end = MaterialTheme.spacing.medium)
                .fillMaxWidth(70f)
                .clip(RoundedCornerShape(10))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = stringResource(id = title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10))
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Text(
                text = stringResource(id = title),
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun RealisticAppBottomBar(modifier: Modifier) {
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(selected = true, onClick = { }, icon = {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
        }, label = {
            Text(text = stringResource(id = R.string.bottom_navigation_home))
        }, alwaysShowLabel = false,
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = Color.LightGray
        )
        BottomNavigationItem(selected = false, onClick = { }, icon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "profile")
        }, label = {
            Text(text = stringResource(id = R.string.bottom_navigation_profile))
        }, alwaysShowLabel = false,
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = Color.LightGray
        )
    }
}



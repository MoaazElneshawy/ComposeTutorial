package com.moaazelneshawy.myapplication.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
* if we want to declare common values like DP and be shared inside the App Theme and be accessible like Colors and Typography
* */

data class Spacing(
    // it's good to have default value
    val default: Dp = 0.dp,
    val small: Dp = 5.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 15.dp
)

/*
* we have "Local" which is commonly used inside compose
* for example: we have LocalContext which gives us the context,
* also we have LocalColors , so we can do it like that using compositionLocalOf
* and customize the app theme
*
* */
val LocalSpacing = compositionLocalOf { Spacing() }


@Composable
fun CustomThemeTest() {
    val smallDp = LocalSpacing.current.small
}

/*
* but what if we want to access it using MaterialTheme not LocalSpacing directly
* */

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

/*
* ------------------------------------------------------------------------------
*/
data class CornersCurves(
    val smallCurve: Dp = 4.dp,
    val mediumCurve: Dp = 7.dp,
    val largeCurve: Dp = 10.dp,
)

val LocalCurves = compositionLocalOf { CornersCurves() }

val MaterialTheme.cornerCurves: CornersCurves
    @Composable
    @ReadOnlyComposable
    get() = LocalCurves.current


@Composable
fun CustomThemeViaMaterial() {
    val smallDp = MaterialTheme.spacing.small
    val largeCurve = MaterialTheme.cornerCurves.largeCurve
}



package com.moaazelneshawy.myapplication

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
*
* For using bottom sheet , we need scaffold
* */

@ExperimentalMaterialApi
@Composable
fun BottomSheetExample() {

    // we use sheet state to toggle if it's expanded or not
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
        // we can add animation
        , animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    // we use scaffoldState and assign this state to the Scaffold
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    // not adding height will make it wrapped to content
                    .height(100.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "Hey", color = Color.White)
            }
        },
        sheetBackgroundColor = Color.DarkGray,
        sheetPeekHeight = 0.dp
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            }) {
                Text(text = "Toggle Sheet", color = Color.White)
            }
        }
    }

}
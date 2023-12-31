package com.moaazelneshawy.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moaazelneshawy.myapplication.androidTutorials.RealisticApp
import com.moaazelneshawy.myapplication.philippVideos.CreateColorsColumn
import com.moaazelneshawy.myapplication.ui.theme.MyApplicationTheme

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealisticApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CreateColorsColumn()
    }
}

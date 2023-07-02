package com.moaazelneshawy.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moaazelneshawy.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var timeOut = { Log.e("MainActivity", "onCreate: 11") }
            RememberUpdatedStateIssue { timeOut.invoke() }
            RememberUpdateStateSolution {
                timeOut.invoke()
            }
            timeOut = { Log.e("MainActivity", "onCreate: 44") }
            timeOut = { Log.e("MainActivity", "onCreate: 55") }
            timeOut = { Log.e("MainActivity", "onCreate: 55") }
            timeOut = { Log.e("MainActivity", "onCreate: 66") }

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

package com.moaazelneshawy.myapplication

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Greeting("Android")
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    Spacer(modifier = Modifier.height(10.dp))
    BlueButton(text = "click") {
        Log.e("TAG", "Greeting: ")
    }
}

@Composable
fun BlueButton(text: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(25f)

    ) {
        Text(text = text)
    }
}
/*

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        TextStyling()
    }
}*/

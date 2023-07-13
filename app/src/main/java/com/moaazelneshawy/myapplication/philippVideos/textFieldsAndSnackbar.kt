 package com.moaazelneshawy.myapplication.philippVideos

 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.height
 import androidx.compose.material.Button
 import androidx.compose.material.Scaffold
 import androidx.compose.material.Text
 import androidx.compose.material.TextField
 import androidx.compose.material.rememberScaffoldState
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.rememberCoroutineScope
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.unit.dp
 import kotlinx.coroutines.launch

 @Composable
fun TextFieldAndSnackbar() {

    var textFieldValue by remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState() // needed to allow you to show normal snackbar

    val scope = rememberCoroutineScope() // scope needed to show snackbar,
    /*
        using scope is not correct to launch inside Composable but it's okay inside callbacks like onClick
     */

    Scaffold(
        modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = {
                    Text(text = "Enter your name") // looks like hint
                }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello $textFieldValue")
                    }
                }) {
                    Text(text = "Greeting")
                }
            }

        }
    }

}

/*

  val textFieldValue by remember {
        mutableStateOf("")
    }
 textFieldValue is now a string and we can set value or use it without .value

    BUT
 val textFieldValue = remember {
        mutableStateOf("")
    }

    textFieldValue is now a state value and we use it with .value

* */
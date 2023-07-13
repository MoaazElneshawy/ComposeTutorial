package com.moaazelneshawy.myapplication.philippVideos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

/*
*
* 1- in build.gradle
*  add implementation "androidx.navigation:navigation-compose:2.4.0-alpha04"
*
* 2- The navController access routes as String
* */

sealed class Screens(val route: String) {

    object RegistrationScreen : Screens("regi_screen")
    object WelcomeScreen : Screens("welcome_screen")

    /*
    * This function make it easier to append args to route
    * */

    fun withArgs(vararg ages: String?): String {
        return buildString {
            append(route)
            /*
            * using slash "/arg" make it required and application may crash if there's default value
            * if we make it "?arg=arg" this means it's an optional
            * */
            ages.forEach {
                if (it.isNullOrEmpty().not())
                    append("/$it")
                else append("?$it=$it")
            }
        }
    }

}

/*
* 3- define the Navigation
* */

const val NAME = "name"
const val AGE = "age"
const val EMAIL = "email"

@Composable
fun NavigationExample() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.RegistrationScreen.route,
        builder = {
            /*
            * 4- here you define the nav graph screens using composable(route){ what it's represent}
            * */

            composable(route = Screens.RegistrationScreen.route) {
                RegistrationScreen(navController = navController)
            }
            /*
            * ORDER IS A MUST
            * */
            composable(route = Screens.WelcomeScreen.route + "/{$NAME}?$AGE=$AGE/{$EMAIL}",
                /*
                 * here we define the arguments
                 * */
                arguments = listOf(
                    navArgument(NAME) {
                        nullable = false
                        defaultValue = "noName"
                        type = NavType.StringType
                    },
                    navArgument(AGE) {
                        nullable = true
                    },
                    navArgument(EMAIL) {
                        nullable = false
                        defaultValue = "m@m.m"
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                WelcomeScreen(
                    entry.arguments?.getString(NAME),
                    entry.arguments?.getString(EMAIL),
                    entry.arguments?.getString(AGE)
                )
            }


        })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(navController: NavController) {

    val ageFocusRequester = remember {
        FocusRequester()
    }
    val emailFocusRequester = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    var userName by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                ageFocusRequester.requestFocus()
            })
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            modifier = Modifier
                .focusRequester(ageFocusRequester)
                .fillMaxWidth(),
            label = { Text(text = "Age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                emailFocusRequester.requestFocus()
            })
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .focusRequester(emailFocusRequester)
                .fillMaxWidth(),
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )

        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(onClick = {
                navController.navigate(Screens.WelcomeScreen.withArgs(userName, age, email))
            }) {
                Text(text = "Register")
            }
        }
    }

}

@Composable
fun WelcomeScreen(name: String?, email: String?, age: String?) {
    Text(text = "Hello $name , $age , $email")
}
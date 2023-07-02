package com.moaazelneshawy.myapplication

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

/*
*
    implementation "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10"

    For create ConstraintLayout , you must have constraintSet
    1- you can assign each constraint with createRefFor
    2- assign value to each item using constraint

       createHorizontalChain(userNameCons, helloBTNCons, chainStyle = ChainStyle.Spread)
       * to control spaces between the constraint you pass

       createGuideline
       * if you want to use guidelines

     modifier = Modifier.layoutId(userNameRef))
     * you can assign id to compose items
*
*
* */

val userNameRef = "userNameRef"
val helloBTNRef = "helloBTNRef"

@Composable
fun CreateConstraintLayout(ctx: Context) {

    var usernameState by remember {
        mutableStateOf("")
    }

    val constraintsSet = ConstraintSet {
        val userNameCons = createRefFor(userNameRef)
        val helloBTNCons = createRefFor(helloBTNRef)

        val guidLine = createGuidelineFromTop(15.dp)

        constrain(userNameCons) {
            top.linkTo(guidLine)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(helloBTNCons.start)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        constrain(helloBTNCons) {
            top.linkTo(guidLine)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

    }

    ConstraintLayout(constraintsSet, modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = usernameState, onValueChange = { usernameState = it },
            modifier = Modifier.layoutId(userNameRef),
            label = {
                Text(text = "enter your name")
            }
        )
        Button(onClick = {
            Toast.makeText(ctx, "Hello $usernameState", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.layoutId(helloBTNRef)) {
            Text(text = "Hello")
        }
    }

}
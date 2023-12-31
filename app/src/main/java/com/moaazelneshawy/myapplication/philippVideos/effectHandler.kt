package com.moaazelneshawy.myapplication.philippVideos

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* There's side effects in compose, when you try to call a non compose code inside compose
* ex: when you try to do network call inside a compose that's a side effect.
* ex: when you try to increase number inside compose view , that's also a side effect.
*
* 1- LaunchedEffect(key: Any, block: CoroutinesScope)
* @Param key: could be anything like boolean or state value,
*             this means this block will cancelled and relaunched again evey time the key changes
*
*
* */

@Composable
fun LaunchedEffectExample(callApi: Boolean) {
    LaunchedEffect(key1 = callApi, block = {
        delay(500)
        Log.e("LaunchedEffectExample: ", "call api")
    })
}

@Composable
fun CallLaunchedEffect() {
    var callApiState by remember {
        mutableStateOf(true)
    }
    Button(onClick = {
        callApiState = callApiState.not()
    }) {
        Text(text = "Click")
    }
    LaunchedEffectExample(callApi = callApiState)

}

/*
* 2- rememberCoroutineScope:
* We can use it to launch a coroutine inside a compose without recomposing the scope.
* This ONLY used inside the callbacks like onClick.
* */

@Composable
fun CoroutineScopeExample() {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            delay(500)
            Log.e("CoroutineScopeExample: ", "print ...")
        }
    }) {
        Text("Click")
    }
}

/*
* 3- rememberUpdatedState:
* the issue with the code below is whenever the onTimeOut updated the new value will not applied.
* because the LaunchedEffect key is still as it's.
* */

@Composable
fun RememberUpdatedStateIssue(onTimeOut: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        Log.e("TAG", "RememberUpdatedStateIssue: ")
        onTimeOut.invoke()
    }
}

/*
* the solution is using the rememberUpdatedState(newValue = Any)
* the code will be executed after the delay but with the new value of the onTimeOut
* */

@Composable
fun RememberUpdateStateSolution(onTimeOut: () -> Unit) {
    val onTimeOutUpdate by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(key1 = true) {
        delay(1000)
        Log.e("TAG", "RememberUpdateStateSolution: ")
        onTimeOutUpdate.invoke()
    }
}

/*
* 4- DisposableEffects:
* we use it with anything that need a clean up like LifeCycle
* this example, if you want to do something on life cycle events changes
* The problem here that we if we leave the compose the observer is still live and this causes leaks
* */

@Composable
fun DisposableEffectExample() {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                Log.e("DisposableEffectExample", "onPause")
            }
        }

        // 1- you have to add the observer to the life cycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // 2- handle disposing this observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

}

/*
*
* 5- SideEffect:
* This is called every successful recomposition
*
* */

@Composable
fun SideEffectExample() {
    SideEffect {
        Log.e("SideEffectExample: ", "recomposed successfully")
    }
}

/*
* 6- ProduceState:
* The usage of produceState that it returns a state changes over time, looks like flow
* */
@Composable
fun ProduceStateExample(counter: Int): State<Int> {
    return produceState(initialValue = 1, producer = {
        while (value < counter) {
            delay(1000L)
            Log.e("ProduceStateExample: ", "$value")
            value++
        }
    })
}

/*
* 7- DerivedStateOf:
* This state saves a cached value of it's scope.
* Any thing calls this value will get the cached value and will not redo or invoke the scope again
* but if there's any change inside the scope, they will be notified with the new value
* */

@Composable
fun DerivedStateExample() {
    var counter by remember {
        mutableStateOf(0)
    }

//    val counterText = "Counter : $counter"
    val counterText by derivedStateOf {
        "Counter : $counter"
    }
    Column {

        Button(onClick = { counter++ }) {
            Text(text = counterText)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { counter++ }) {
            Text(text = "BTN 2 $counterText")
        }
    }
}
package com.moaazelneshawy.myapplication.philippVideos

import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

/*
*
* Case 1 :
* use compose inside xml,
* in the layout.xml
*       <androidx.compose.ui.platform.ComposeView
*               android:id="@+id/myComposeView
*               android:layout_width=" ... etc />
*
*
* inside the code Activity.kt, get access to the myComposeView
*
*   myComposeView.apply{
*
*       setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
*       // this is required for decomposition after the activity or fragment get destroyed
*
*       setContent{
*           // here we can use the Compose widgets like Text , Button ..etc
*           Text(text="hello"))
*          }
*   }
*
* */

/*
* case 2:
* use xml view with compose,
* This maybe needed if we want to use xml view if we don't have compose for it like MapView for example
* */

@Composable
fun MigrateWithXml() {
    Column(Modifier.fillMaxSize()) {

        AndroidView(
            // inside factory we specify the view we will use
            factory = {
                TextView(it)
            }) { tv ->
            // Here we can assign the properties for the TextView
            tv.apply {
                text = "TextView as Compose !"
                setTextColor(Color.BLUE)
                gravity = Gravity.CENTER
            }
        }

        Spacer(Modifier.height(15.dp))
        Text(text = "Normal Text Compose")
    }
}
package com.moaazelneshawy.myapplication.philippVideos

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

/*
* creating a list with selected and non selected items
* */

data class ListItem(
    val title: String,
    var isSelected: Boolean = false
)

const val btn_collectFiltered = "btn_collectFiltered"
const val lc_list = "lc_list"

@Composable
fun SelectedItemsExample() {

    val context = LocalContext.current

    var itemsList by remember {
        mutableStateOf(
            (1..20).map {
                ListItem("item $it")
            }
        )
    }

    val constraintSet = ConstraintSet {
        val btn = createRefFor(btn_collectFiltered)
        val list = createRefFor(lc_list)

        constrain(list) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(btn.top)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        constrain(btn) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            top.linkTo(list.bottom)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.preferredWrapContent
        }
    }


    ConstraintLayout(constraintSet = constraintSet, modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = {
                val selectedCount = itemsList.filter { it.isSelected }.size
                Toast.makeText(context, "Selected Count : $selectedCount", Toast.LENGTH_SHORT)
                    .show()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .layoutId(btn_collectFiltered)
        ) {
            Text(text = "Get Selected")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(lc_list)
        ) {

            itemsIndexed(itemsList) { itemIndex, item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            itemsList = itemsList.mapIndexed { index, listItem ->
                                if (itemIndex == index) {
                                    item.copy(isSelected = listItem.isSelected.not())
                                } else listItem
                            }
                        }
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = item.title)

                    if (item.isSelected) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            tint = Color.Blue,
                            contentDescription = "if item checked"
                        )
                    }

                }

            }
        }

    }

}
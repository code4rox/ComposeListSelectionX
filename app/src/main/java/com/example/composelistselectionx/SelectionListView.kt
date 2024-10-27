package com.example.composelistselectionx

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SelectionListView(
    modifier: Modifier,
    dataList: ArrayList<Item>,
    selectionHandler: SelectionHandlerX<Item>
) {
    val context = LocalContext.current
    LazyColumn(modifier = modifier) {
        itemsIndexed(dataList) { position, item ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(if (item.isSelected.value) LightGray else Color.Transparent)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            selectionHandler.enableSelection(dataList, position)
                        },
                        onTap = {
                            selectionHandler.toggleSelection(position){
                                Toast.makeText(context, "${item.name} Clicked", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp), text = item.name,
                    style = TextStyle(fontSize = 16.sp)
                )
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .background(LightGray)
                )
            }


        }
    }
}
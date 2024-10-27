package com.example.composelistselectionx

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun DeleteSelectedItemView(dataList: ArrayList<Item>, selectionHandler: SelectionHandlerX<Item>) {
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Color.Black)
        .clickable {
            val selectedItems = selectionHandler.getSelectedItems()
            selectedItems.forEach { item ->
                dataList.remove(item)
            }
            selectionHandler.disableSelection()
            Toast
                .makeText(context, "${selectedItems.size} items deleted", Toast.LENGTH_SHORT)
                .show()
        })
    {
        Text(
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            text = "Delete Items"
        )
    }

}

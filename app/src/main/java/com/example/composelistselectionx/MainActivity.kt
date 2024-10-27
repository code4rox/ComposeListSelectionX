package com.example.composelistselectionx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.composelistselectionx.ui.theme.ComposeListSelectionXTheme


class MainActivity : ComponentActivity() {

    private val selectionHandler = SelectionHandlerX<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListSelectionXTheme {
                MainScreen(selectionHandler)
            }
        }
    }
}

@Composable
fun MainScreen(selectionHandler: SelectionHandlerX<Item>) {
    // Sample data
    val dataList = remember { dataList() }
    Column {
        InitAppBar(selectionHandler)
        SelectionListView(modifier = Modifier.weight(1f), dataList, selectionHandler)

        // Show delete selected items view
        if (selectionHandler.isSelectionEnable.value) {
            DeleteSelectedItemView(dataList, selectionHandler)
        }
    }
}

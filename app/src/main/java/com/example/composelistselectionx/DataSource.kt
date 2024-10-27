package com.example.composelistselectionx

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


fun dataList(): ArrayList<Item> {
    val items = ArrayList<Item>()
    for (i in 1..100) {
        items.add(Item("Item $i"))
    }
    return items
}


data class Item(
    val name: String,
    override var isSelected: MutableState<Boolean> = mutableStateOf(false)
) : BaseSelection


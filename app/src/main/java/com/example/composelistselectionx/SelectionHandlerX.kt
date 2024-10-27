package com.example.composelistselectionx

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

// Define an interface for any selectable item, requiring an isSelected state.
interface BaseSelection {
    var isSelected: MutableState<Boolean>
}

// Generic class to handle selection operations on a list of items implementing BaseSelection.
class SelectionHandlerX<T : BaseSelection> {

    private var selectableList = emptyList<T>()

    // State to enable or disable selection mode.
    var isSelectionEnable: MutableState<Boolean> = mutableStateOf(false)

    // State to keep track of the "Select All" toggle status.
    var isSelectAll: MutableState<Boolean> = mutableStateOf(false)

    // Keeps track of the number of selected items.
    var selectionCount: MutableState<Int> = mutableIntStateOf(0)

    fun getItemList() = selectableList

    fun selectAll() {
        selectableList.forEach { it.isSelected.value = true }
        selectionCount.value = selectableList.size
        isSelectAll.value = true
    }

    // Initializes the selection with a list of items, enables selection mode
    fun enableSelection(selectableList: List<T>, position: Int) {
        this.selectableList = selectableList
        isSelectionEnable.value = true
        toggleSelection(position)
    }

    // Disables selection mode and clears all selections.
    fun disableSelection() {
        isSelectionEnable.value = false
        clearSelection()
    }

    // Toggles selection of a specific item by its position.
    fun toggleSelection(position: Int, onItemClick: (() -> Unit)? = null) {

        // If selection mode is not enabled, triggers the optional onItemClick callback instead.
        if (!isSelectionEnable.value) {
            onItemClick?.invoke()
            return
        }
        val item = selectableList[position]
        item.isSelected.value = !item.isSelected.value

        // Update the selection count.
        selectionCount.value += if (item.isSelected.value) 1 else -1

        // Update the "Select All" state based on selection count.
        if (selectionCount.value == selectableList.size) {
            isSelectAll.value = true
        } else if (selectionCount.value == 0 || selectionCount.value < selectableList.size) {
            isSelectAll.value = false
        }
    }

    fun getSelectedItems(): List<T> = selectableList.filter { it.isSelected.value }

    // Clears all selections and resets the count.`
    fun clearSelection() {
        selectionCount.value = 0
        selectableList.forEach { it.isSelected.value = false }
    }
}
# SelectionHandlerX

`SelectionHandlerX` is a Kotlin library for managing item selection in Jetpack Compose applications. This generic utility class allows you to enable, disable, and manage the selection state of a list of items, making it easy to implement features like multi-select, select all, and single-item toggles.


![Untitledvideo-MadewithClipchamp1-ezgif com-crop (1)](https://github.com/user-attachments/assets/78a22c1b-20fc-423f-9113-2acfa964f7a5)

## Features

- **Select All / Deselect All**: Easily enable "Select All" functionality across your items.
- **Toggle Selection**: Toggle the selection of individual items.
- **Track Selection Count**: Track the number of selected items and update views based on the selection state.
- **Enable / Disable Selection Mode**: Control when the selection is enabled or disabled.

## Sample Project 

See complete 

## Installation

Add `SelectionHandlerX.kt` to your Jetpack Compose project.

1. Download or clone the repository:

   ```bash
   git clone https://github.com/code4rox/ComposeListSelectionX.git
   ```

2. Copy the `SelectionHandlerX.kt` file into your project’s package structure.

## Usage

### 1. Implementing `BaseSelection` Interface

First, create a data class for your items that implements the `BaseSelection` interface. This interface requires an `isSelected` state.

```kotlin
data class Item(
    val name: String,
    override var isSelected: MutableState<Boolean> = mutableStateOf(false)
) : BaseSelection
```

### 2. Initialize `SelectionHandlerX`

Create an instance of `SelectionHandlerX` and pass your item type.

```kotlin
val selectionHandler = SelectionHandlerX<Item>()
```

### 3. Enabling Selection

Enable selection mode by calling `enableSelection` with your list of items and the position of the item to toggle.

```kotlin
selectionHandler.enableSelection(itemList, position) // Enables selection 
```
Example : in lazycolumn long press on item

```kotlin
...
 Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            selectionHandler.enableSelection(dataList, position)
                        },
                        onTap = {
                           -------
                        }
                    )
                }
....
```
### 4. Toggling Selection

Toggle selection for an item by its position. You can also pass an `onItemClick` callback.

```kotlin
selectionHandler.toggleSelection(position) {
    // Action when item click is outside selection mode
}
```

Example : in lazycolumn click on item

```kotlin
...
 Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                          ------
                        },
                        onTap = {
                          selectionHandler.toggleSelection(position){
                                Toast.makeText(context, "${item.name} Clicked", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
....
```

### 5. Selecting All Items

To select all items, call `selectAll()`:

```kotlin
selectionHandler.selectAll()
```

### 6. Clearing Selection

To clear all selection 

```kotlin
selectionHandler.clearSelection()
```

### 6. disableSelection Selection

To deselect all items and clear selection mode, use `disableSelection()`:

```kotlin
selectionHandler.disableSelection()
```

### 7. Retrieving Selected Items

To get a list of all selected items:

```kotlin
val selectedItems = selectionHandler.getSelectedItems()
```

## API Overview

| Function                   | Description                                                                                       |
|----------------------------|---------------------------------------------------------------------------------------------------|
| `enableSelection`          | Enables selection mode and toggles the specified item.                                            |
| `disableSelection`         | Disables selection mode and clears all selections.                                                |
| `selectAll`                | Selects all items in the list.                                                                    |
| `toggleSelection`          | Toggles the selection state of a specific item and updates selection count.                       |
| `getSelectedItems`         | Returns a list of currently selected items.                                                       |
| `clearSelection`           | Clears all selections.                                                                            |

## Contributing

Feel free to contribute by opening issues or pull requests. Make sure to follow best practices and write clear commit messages.

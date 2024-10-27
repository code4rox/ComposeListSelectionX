package com.example.composelistselectionx

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InitAppBar(selectionHandler: SelectionHandlerX<Item>) {

    if (!selectionHandler.isSelectionEnable.value) {
        Row(
            modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
                .background(Black)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                text = "Compose List Selection Example",
                color = White,
                style = TextStyle(fontSize = 20.sp)
            )
        }
    } else {
        InitSelectionAppBar(
            //get selection count
            selectionCount = selectionHandler.selectionCount.value,
            //get selection state
            isAllItemsSelected = selectionHandler.isSelectAll,

            onAllSelectionChange = {
                selectionHandler.isSelectAll.value = it
                if (it) selectionHandler.selectAll() else selectionHandler.clearSelection()
            },
            //clear selection
            onClearSelection = { selectionHandler.disableSelection() })
    }


}

@Composable
fun InitSelectionAppBar(
    selectionCount: Int,
    isAllItemsSelected: MutableState<Boolean>,
    onAllSelectionChange: (Boolean) -> Unit,
    onClearSelection: () -> Unit,
) {

    val iconModifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .width(28.dp)
        .height(28.dp)

    Row(
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .background(Black)
            .clickable { }
            .padding(horizontal = 10.dp)
    ) {
        Icon(
            modifier = iconModifier
                .align(Alignment.CenterVertically)
                .clickable {
                    onClearSelection()
                },
            tint = White,
            imageVector = Icons.Filled.Close,
            contentDescription = ""
        )

        Spacer(modifier = Modifier.padding(15.dp))

        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            text = "$selectionCount",
            color = White,
            style = TextStyle(fontSize = 25.sp)
        )

        Spacer(modifier = Modifier.padding(5.dp))


        CustomCheckbox(
            modifier = iconModifier
                .align(Alignment.CenterVertically),
            checked = isAllItemsSelected.value,
            onCheckedChange = {
                onAllSelectionChange(it)
            }
        )
    }

}


@Composable
fun CustomCheckbox(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Icon(
        modifier = modifier.clickable {
            onCheckedChange(!checked)
        },
        tint = White,
        painter = painterResource(id = if (checked) R.drawable.ic_check else R.drawable.ic_un_check),
        contentDescription = "Checked"
    )
}


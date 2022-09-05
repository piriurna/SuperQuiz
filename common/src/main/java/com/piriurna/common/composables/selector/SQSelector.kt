package com.piriurna.common.composables.selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.models.SelectableItem
import java.lang.Integer.max
import kotlin.math.min

@Composable
fun SQSelector(
    modifier: Modifier = Modifier,
    items: List<SelectableItem>,
    initialItemIndex: Int = 0,
    onItemChanged : (SelectableItem) -> Unit = {},
    onNextPressed : () -> Unit = {},
    onPreviousPressed : () -> Unit = {}
) {
    var selectedIndex by remember {
        mutableStateOf(initialItemIndex)
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(items.isNotEmpty()){
            val selectedItem = items[selectedIndex]

            Icon(
                modifier = Modifier.clickable {
                    selectedIndex = max(0, selectedIndex - 1)
                    onItemChanged(items[selectedIndex])
                    onPreviousPressed
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous Button"
            )

            Text(text = selectedItem.name)

            Icon(
                modifier = Modifier.clickable {
                    selectedIndex = min(items.size - 1, selectedIndex + 1)
                    onItemChanged(items[selectedIndex])
                    onNextPressed()
                },
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next Button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SQSelectorPreview() {
    Box(modifier = Modifier.fillMaxSize()){
        SQSelector(items = listOf(
            SelectableItem("0", "Categoria 1"),
            SelectableItem("1", "Categoria 2"),
        ))
    }
}
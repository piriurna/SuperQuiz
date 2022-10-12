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
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.models.SelectableItem
import com.piriurna.common.theme.SQStyle
import java.lang.Integer.max
import kotlin.math.min

@Composable
fun SQSelector(
    modifier: Modifier = Modifier,
    item : SelectableItem,
    onNextPressed : () -> Unit = {},
    onPreviousPressed : () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.clickable {
                onPreviousPressed()
            },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Previous Button"
        )

        SQText(text = item.name, style = SQStyle.TextLatoBold)

        Icon(
            modifier = Modifier.clickable {
                onNextPressed()
            },
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next Button"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SQSelectorPreview() {
    Box(modifier = Modifier.fillMaxSize()){
        SQSelector(
            item = SelectableItem("9", "Category 1"),
        )
    }
}
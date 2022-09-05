package com.piriurna.common.composables.selector

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SQSwitch(
    modifier: Modifier = Modifier,
    checked : Boolean,
    onCheckedChange : (Boolean) -> Unit
) {
    Switch(checked = checked, onCheckedChange = onCheckedChange)
}


@Preview(showBackground = true)
@Composable
fun SQSwitchPreview() {
    Column() {
        SQSwitch(checked = true, onCheckedChange = {})
    }
}
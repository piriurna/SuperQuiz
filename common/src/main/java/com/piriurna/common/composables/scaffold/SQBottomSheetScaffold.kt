package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterialApi
@Composable
fun SQBottomSheetScaffold(
    modifier: Modifier = Modifier,
    sheetContent : @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    BottomSheetScaffold(modifier=modifier, content = content, sheetContent = {

        Box(Modifier.fillMaxSize()) {

        }

    })
}

@Preview(showBackground = true)
@Composable
fun SQBottomSheetScaffoldPreview() {

}
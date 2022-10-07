package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.piriurna.common.theme.primaryGreen

@ExperimentalMaterialApi
@Composable
fun SQBottomSheetScaffold(
    modifier: Modifier = Modifier,
    sheetContent : @Composable ColumnScope.() -> Unit,
    sheetPeekHeight : Dp = 50.dp,
    sheetState : BottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed),
    content: @Composable (PaddingValues) -> Unit,
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        modifier=modifier,
        scaffoldState = scaffoldState,
        content = content,
        sheetContent = {
            sheetContent.invoke(this)
        },
        sheetBackgroundColor = Color.Transparent,
        sheetPeekHeight = sheetPeekHeight
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun SQBottomSheetScaffoldPreview() {
    SQBottomSheetScaffold(
        sheetContent = {

        },
        content = { Column(
            Modifier
                .fillMaxSize()
                .background(primaryGreen)) {

        }}
    )
}
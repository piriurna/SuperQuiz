package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.composables.loading.SQLoading

@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    content: @Composable (PaddingValues) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var scaffoldModifier = modifier.fillMaxSize()

        Scaffold(
            modifier = scaffoldModifier,
            content = content
        )

        SQLoading(isLoading = isLoading)
    }
}


@Preview(showBackground = true)
@Composable
fun SQScaffoldPreview() {
    SQScaffold(isLoading = true) {
        Text(text = "Scaffold test")
    }
}
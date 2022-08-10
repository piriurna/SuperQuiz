package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.loading.SQLoading
import com.piriurna.common.composables.navigation.SQBottomNavigation
import com.piriurna.common.composables.theme.purple
import com.piriurna.common.models.BottomNavigationItem

@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    content: @Composable (PaddingValues) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var scaffoldModifier = modifier.fillMaxSize()

        Scaffold(
            modifier = scaffoldModifier,
            content = content,
            bottomBar = {
                if(bottomBarItems.isNotEmpty()) {
                    SQBottomNavigation(
                        selectedColor = purple,
                        unselectedColor = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        items = bottomBarItems,
                        selectedRoute = ""
                    )
                }
            }
        )

        SQLoading(isLoading = isLoading)
    }
}


@Preview(showBackground = true)
@Composable
fun SQScaffoldPreview() {
    SQScaffold(
        isLoading = false,
        bottomBarItems = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile)
    ) {
        Text(text = "Scaffold test")
    }
}
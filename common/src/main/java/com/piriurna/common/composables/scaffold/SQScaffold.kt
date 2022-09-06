package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.composables.loading.SQLoading
import com.piriurna.common.composables.navigation.SQBottomNavigation
import com.piriurna.common.theme.purple
import com.piriurna.common.models.BottomNavigationItem

@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    onItemSelected : (BottomNavigationItem) -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val scaffoldModifier = modifier.fillMaxSize()

        Scaffold(
            modifier = scaffoldModifier,
            content = content,
            bottomBar = {
                if(bottomBarItems.isNotEmpty()) {
                    SQBottomNavigation(
                        selectedColor = purple,
                        unselectedColor = Color.LightGray,
                        items = bottomBarItems,
                        selectedRoute = bottomBarItems[0].route,
                        onItemSelected = onItemSelected
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
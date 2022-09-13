package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQAppBarIcon
import com.piriurna.common.composables.loading.SQLoading
import com.piriurna.common.composables.navigation.SQBottomNavigation
import com.piriurna.common.composables.toolbar.SQAppBar
import com.piriurna.common.theme.purple
import com.piriurna.common.models.BottomNavigationItem

/**
 * @param appBarBackIcon should typically be an [SQAppBarIcon], using an icon from
 * [androidx.compose.material.icons.Icons]
 *
 * @param appBarOptionsIcon should typically be an [SQAppBarIcon], using an icon from
 * [androidx.compose.material.icons.Icons]
 */
@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    hasToolbar: Boolean,
    appBarTitle: String? = null,
    appBarBackIcon: @Composable () -> Unit = {},
    appBarOptionsIcon: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val scaffoldModifier = modifier.fillMaxSize()
        Scaffold(
            modifier = scaffoldModifier,
            content = content,
            topBar = {

            },
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

        if(hasToolbar)
            SQAppBar(
                modifier = Modifier.padding(top = 20.dp).align(Alignment.TopCenter),
                title = appBarTitle,
                backIcon = appBarBackIcon,
                optionsIcon = appBarOptionsIcon,
            )

        SQLoading(isLoading = isLoading)
    }
}


@Preview(showBackground = true)
@Composable
fun SQScaffoldPreview() {
    SQScaffold(
        isLoading = false,
        bottomBarItems = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile),
        hasToolbar = true,
        appBarTitle = "This is the toolbar",
        appBarBackIcon = {},
        appBarOptionsIcon = {
            SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
        }
    ) {
        Text(text = "Scaffold test")
    }
}
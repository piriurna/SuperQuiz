package com.piriurna.common.composables.scaffold

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.piriurna.common.composables.button.SQAppBarIcon
import com.piriurna.common.composables.loading.SQLoading
import com.piriurna.common.composables.navigation.SQBottomNavigation
import com.piriurna.common.composables.toolbar.AppBarOptions
import com.piriurna.common.composables.toolbar.SQAppBar
import com.piriurna.common.theme.purple
import com.piriurna.common.models.BottomNavigationItem
import kotlin.math.roundToInt


@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    bottomBarItems: List<BottomNavigationItem> = emptyList(),
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    appBarOptions: AppBarOptions? = null,
    navController: NavHostController? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    val bottomBarHeight = 52.dp

    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value =
                    newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val scaffoldModifier = modifier.fillMaxSize()
        Scaffold(
            modifier = scaffoldModifier.nestedScroll(nestedScrollConnection),
            content = content,
            bottomBar = {
                SQBottomNavigation(
                    modifier = Modifier
                        .height(bottomBarHeight)
                        .offset {
                            IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt())
                        },
                    unselectedColor = Color.LightGray,
                    items = bottomBarItems,
                    onItemSelected = onItemSelected,
                    navController = navController
                )
            }
        )

        if(appBarOptions != null)
            SQAppBar(
                modifier = Modifier.padding(top = 20.dp).align(Alignment.TopCenter),
                appBarOptions = appBarOptions
            )

        SQLoading(isLoading = isLoading)
    }
}


@Preview(showBackground = true)
@Composable
fun SQScaffoldPreview() {
    SQScaffold(
        isLoading = true,
        bottomBarItems = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile),
        appBarOptions = AppBarOptions.AppBarWithTitleBackAndOptions(
            appBarTitle = "This is the Appbar Title",
            appBarBackButton = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
            },
            appBarOptionsButton = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
            }
        )
    ) {
        Text(text = "Scaffold test")
    }
}
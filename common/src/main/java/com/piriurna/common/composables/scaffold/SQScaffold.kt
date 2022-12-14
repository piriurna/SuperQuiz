package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.piriurna.common.composables.button.SQAppBarIcon
import com.piriurna.common.composables.error.SQErrorContainer
import com.piriurna.common.composables.loading.SQLoading
import com.piriurna.common.composables.navigation.SQBottomNavigation
import com.piriurna.common.composables.toolbar.AppBarOptions
import com.piriurna.common.composables.toolbar.SQAppBar
import com.piriurna.common.models.BottomNavigationItem
import com.piriurna.common.models.SQError
import com.piriurna.common.theme.secondaryBackground
import kotlin.math.roundToInt


@Composable
fun SQScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error : SQError? = null,
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
        )

        if(appBarOptions != null)
            SQAppBar(
                modifier = Modifier.align(Alignment.TopCenter),
                appBarOptions = appBarOptions
            )

        SQLoading(isLoading = isLoading)

        if(error != null && !isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(enabled = false, onClick = {})
                    .background(MaterialTheme.colors.secondaryBackground.copy(alpha = 0.8f))
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
              SQErrorContainer(
                  imageResource = error.imageResource,
                  title = stringResource(id = error.title),
                  subtitle = stringResource(id = error.subtitle),
                  hasButton = error.canRetry,
                  buttonOnClick = error.onRetry,
                  buttonText = stringResource(id = error.retryText),
              )
            }
        }

        SQBottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
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
}


@Preview(showBackground = true)
@Composable
fun SQScaffoldPreview() {
    SQScaffold(
        isLoading = false,
        error = SQError.GenericError {},
        bottomBarItems = BottomNavigationItem.getMockNavigationItems,
        appBarOptions = AppBarOptions.AppBarWithTitleAndBack(
            appBarTitle = "This is the Appbar Title",
            appBarBackButton = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
            },
            onBackPressed = {}
        )
    ) {
        Text(text = "Scaffold test")
    }
}
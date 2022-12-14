package com.piriurna.common.composables.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.piriurna.common.extensions.getSelected
import com.piriurna.common.models.BottomNavigationItem
import com.piriurna.common.theme.purple

@Composable
fun SQBottomNavigation(
    modifier: Modifier = Modifier,
    unselectedColor: Color,
    items: List<BottomNavigationItem>,
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    navController: NavController?
) {

    val navBackStackEntry = navController?.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.value?.destination

    BuildSQBottomNavigation(
        modifier = modifier,
        isVisible = showBottomBar(currentDestination, items = items),
        selectedRoute = currentDestination?.route,
        unselectedColor = unselectedColor,
        items = items,
        onItemSelected = onItemSelected
    )

}

@Composable
fun BuildSQBottomNavigation(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    unselectedColor: Color,
    items: List<BottomNavigationItem>,
    selectedRoute: String?,
    moveAnimationDuration : Int = 1000,
    indicatorSizeAnimationDuration : Int = 350,
    onItemSelected: (BottomNavigationItem) -> Unit = {},
) {
    if (isVisible) {
        Column(
            modifier = modifier
                .shadow(8.dp)
                .fillMaxWidth()
                .background(Color.White)
        ) {

            val configuration = LocalConfiguration.current

            val screenWidth = configuration.screenWidthDp

            var isMoving by remember {
                mutableStateOf(false)
            }


            val fullIndicatorWidth = 56f

            val indicatorWidth by animateFloatAsState(
                targetValue = if(isMoving) fullIndicatorWidth * 1.6f else fullIndicatorWidth,
                animationSpec = tween(durationMillis = indicatorSizeAnimationDuration, easing = FastOutLinearInEasing),
                finishedListener = { isMoving = false }
            )


            val itemWidth = screenWidth / items.size.toFloat()


            val color by animateColorAsState(
                targetValue = items.getSelected(selectedRoute)?.color?: purple,
                animationSpec = tween(moveAnimationDuration)
            )

            val offsetAnim by animateFloatAsState(
                targetValue = with(LocalDensity.current){ ((itemWidth * items.indexOf(items.getSelected(selectedRoute))) + (itemWidth - fullIndicatorWidth)/2f).dp.toPx() },
                animationSpec = tween(moveAnimationDuration)
            )


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable(enabled = false, onClick = {})
            ){
                items.forEachIndexed { index, item ->
                    SQNavigationItem(
                        modifier = Modifier
                            .width(itemWidth.dp),
                        icon = ImageVector.vectorResource(id = item.iconRes),
                        text = item.title,
                        selected = selectedRoute == item.route,
                        selectedColor = color,
                        unselectedColor = unselectedColor,
                        onClick = {
                            onItemSelected(item)
                            isMoving = true
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(indicatorWidth.dp)
                    .height(3.dp)
                    .offset(with(LocalDensity.current) { (offsetAnim).toDp() })
                    .clip(RoundedCornerShape(5.dp))
                    .background(color)
            )
        }
    }

}

private fun showBottomBar(
    currentDestination: NavDestination?,
    items: List<BottomNavigationItem>) = items.any { it.route == currentDestination?.route }

@Preview(showBackground = true)
@Composable
private fun SQBottomNavigationPreview() {

    Column() {
        BuildSQBottomNavigation(
            isVisible = true,
            unselectedColor = Color.LightGray,
            items = BottomNavigationItem.getMockNavigationItems,
            indicatorSizeAnimationDuration = 350,
            onItemSelected = {},
            selectedRoute = "PROFILE"
        )

        BuildSQBottomNavigation(
            isVisible = true,
            unselectedColor = Color.LightGray,
            items = BottomNavigationItem.getMockNavigationItems,
            indicatorSizeAnimationDuration = 400,
            onItemSelected = {},
            selectedRoute = "PLAY_GAMES"
        )

        BuildSQBottomNavigation(
            isVisible = true,
            unselectedColor = Color.LightGray,
            items = BottomNavigationItem.getMockNavigationItems,
            indicatorSizeAnimationDuration = 500,
            onItemSelected = {},
            selectedRoute = "CHART"
        )
    }
}
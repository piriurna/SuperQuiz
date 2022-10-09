package com.piriurna.common.composables.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.theme.purple
import com.piriurna.common.models.BottomNavigationItem

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
    moveAnimationDuration : Int = 1000,
    indicatorSizeAnimationDuration : Int = 350,
    onItemSelected: (BottomNavigationItem) -> Unit = {},
) {
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

        var currentIndex by remember { mutableStateOf(0) }


        val color by animateColorAsState(
            targetValue = items.getOrElse(currentIndex) {BottomNavigationItem.PlayGames}.color,
            animationSpec = tween(moveAnimationDuration)
        )

        val offsetAnim by animateFloatAsState(
            targetValue = with(LocalDensity.current){ ((itemWidth * currentIndex) + (itemWidth - fullIndicatorWidth)/2f).dp.toPx() },
            animationSpec = tween(moveAnimationDuration)
        )


        if (isVisible) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                items.forEachIndexed { index, item ->
                    SQNavigationItem(
                        modifier = Modifier
                            .width(itemWidth.dp),
                        icon = item.icon,
                        text = item.title,
                        selected = currentIndex == index,
                        selectedColor = color,
                        unselectedColor = unselectedColor,
                        onClick = {
                            onItemSelected(item)
                            isMoving = true
                            currentIndex = index
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
            items = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart, BottomNavigationItem.Profile),
            indicatorSizeAnimationDuration = 350,
            onItemSelected = {},
        )

        BuildSQBottomNavigation(
            isVisible = true,
            unselectedColor = Color.LightGray,
            items = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart),
            indicatorSizeAnimationDuration = 400,
            onItemSelected = {}
        )

        BuildSQBottomNavigation(
            isVisible = true,
            unselectedColor = Color.LightGray,
            items = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart),
            indicatorSizeAnimationDuration = 500,
            onItemSelected = {},
        )
    }
}
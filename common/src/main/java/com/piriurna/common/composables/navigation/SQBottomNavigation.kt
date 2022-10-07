package com.piriurna.common.composables.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    selectedColor : Color,
    unselectedColor: Color,
    items: List<BottomNavigationItem>,
    selectedRoute : String,
    onItemSelected: (BottomNavigationItem) -> Unit = {},
    navController: NavController?
) {

    val navBackStackEntry = navController?.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.value?.destination

    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp

    var selectedItem by remember {
        mutableStateOf(selectedRoute)
    }


    Column {
        var isMoving by remember {
            mutableStateOf(false)
        }

        val animationDuration = 600

        val fullIndicatorWidth = screenWidth / 3f

        val indicatorWidth by animateFloatAsState(
            targetValue = if(isMoving) 15f else fullIndicatorWidth,
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
            finishedListener = { isMoving = false }
        )


        val itemWidth = screenWidth / 3f

        var currentIndex by remember { mutableStateOf(0) }


        val color by animateColorAsState(
            targetValue = items.getOrElse(currentIndex) {BottomNavigationItem.PlayGames}.color,
            animationSpec = tween(animationDuration)
        )

        val offsetAnim by animateFloatAsState(
            targetValue = with(LocalDensity.current){ ((itemWidth * currentIndex) + (itemWidth - fullIndicatorWidth)/2f).dp.toPx() },
            animationSpec = tween(animationDuration)
        )


        if (showBottomBar(currentDestination, items = items)) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                items.forEachIndexed { index, item ->
                    SQNavigationItem(
                        modifier = Modifier
                            .width(itemWidth.dp),
                        icon = item.icon,
                        text = item.title,
                        selected = selectedItem == item.route,
                        selectedColor = color,
                        unselectedColor = unselectedColor,
                        onClick = {
                            onItemSelected(item)
                            isMoving = true
                            currentIndex = index
                            selectedItem = item.route
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .width(indicatorWidth.dp)
                    .height(3.dp)
                    .offset(with(LocalDensity.current) { (offsetAnim).toDp() }, 0.dp)
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
        SQBottomNavigation(
            selectedColor = purple,
            unselectedColor = Color.LightGray,
            modifier = Modifier
                .padding(bottom = 16.dp),
            items = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart),
            selectedRoute = BottomNavigationItem.Profile.route,
            onItemSelected = {
                print("a")
            },
            navController = rememberNavController()
        )
}
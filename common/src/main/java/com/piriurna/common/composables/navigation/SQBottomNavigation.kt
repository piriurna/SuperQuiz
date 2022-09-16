package com.piriurna.common.composables.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember {
        mutableStateOf(selectedRoute)
    }

    if (showBottomBar(currentDestination, items = items)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            items.forEach { item ->
                SQNavigationItem(
                    icon = item.icon,
                    text = item.title,
                    selected = selectedItem == item.route,
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor,
                    onClick = {
                        onItemSelected(item)
                        selectedItem = item.route
                    }
                )
            }
        }
    }
}

private fun showBottomBar(
    currentDestination: NavDestination?,
    items: List<BottomNavigationItem>) = items.any { it.route == currentDestination?.route }

@Preview(showBackground = true)
@Composable
private fun SQBottomNavigationPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SQBottomNavigation(
            selectedColor = purple,
            unselectedColor = Color.LightGray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            items = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart),
            selectedRoute = BottomNavigationItem.Profile.route,
            onItemSelected = {
                print("a")
            },
            navController = rememberNavController()
        )
    }
}
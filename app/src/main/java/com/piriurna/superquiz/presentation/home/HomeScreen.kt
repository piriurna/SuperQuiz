package com.piriurna.superquiz.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.models.BottomNavigationItem
import com.piriurna.superquiz.presentation.navigation.HomeNavigationGraph
import com.piriurna.superquiz.presentation.navigation.models.NavigationOptions

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    SQScaffold(
        bottomBarItems = listOf(NavigationOptions.PlayGames, NavigationOptions.Chart, NavigationOptions.Profile),
        onItemSelected = { item ->
            navController.navigate(item.route)
        },
        navController = navController
    ) {
        HomeNavigationGraph(navController = navController)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
package com.piriurna.superquiz.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.models.BottomNavigationItem
import com.piriurna.superquiz.presentation.navigation.HomeNavigationGraph

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    SQScaffold(
        bottomBarItems = listOf(BottomNavigationItem.PlayGames, BottomNavigationItem.Profile, BottomNavigationItem.Chart),
        onItemSelected = { item ->
            navController.navigate(item.route)
        },
        hasToolbar = false
    ) {
        HomeNavigationGraph(navController = navController)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
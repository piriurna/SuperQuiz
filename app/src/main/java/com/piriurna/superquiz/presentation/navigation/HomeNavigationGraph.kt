package com.piriurna.superquiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.superquiz.presentation.chart.ChartScreen
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.playgames.PlayGamesScreen
import com.piriurna.superquiz.presentation.profile.ProfileScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = HomeDestinationScreen.PlayGames.route
    ) {

        playGamesNavigationGraph(navController = navController)

        composable(route = HomeDestinationScreen.PlayGames.route) {
            PlayGamesScreen(navController)
        }

        composable(route = HomeDestinationScreen.Chart.route) {
            ChartScreen(navController)
        }


        settingsNavigationGraph(navController = navController)

        composable(route = HomeDestinationScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }

}

sealed class HomeDestinationScreen(val route: String, val arguments : String = "") {
    object PlayGames : HomeDestinationScreen(route = "PLAY_GAMES")
    object Chart : HomeDestinationScreen(route = "CHART")
    object Profile : HomeDestinationScreen(route = "PROFILE")
    object UserSettings : HomeDestinationScreen(route = "USER_SETTINGS")
}

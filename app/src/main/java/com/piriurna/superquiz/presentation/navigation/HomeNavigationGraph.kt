package com.piriurna.superquiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.superquiz.presentation.home.HomeScreen
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.playgames.PlayGamesScreen
import com.piriurna.superquiz.presentation.splash.SplashScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME_GRAPH,
        startDestination = HomeDestinationScreen.PlayGames.route
    ) {

        //--authenticationNavGraph(navController = navController)

        composable(route = HomeDestinationScreen.PlayGames.route) {
            PlayGamesScreen()
        }

//        composable(route = RootDestinationScreen.Splash.route) {
//            SplashScreen(navController = navController)
//        }
    }

}

sealed class HomeDestinationScreen(val route: String) {
    object PlayGames : RootDestinationScreen(route = "PLAY_GAMES")
}
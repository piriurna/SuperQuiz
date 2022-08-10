package com.piriurna.superquiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.splash.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT_GRAPH,
        startDestination = RootDestinationScreen.Splash.route
    ) {

        //--authenticationNavGraph(navController = navController)

//        composable(route = RootDestinationScreen.Home.route) {
//            HomeScreen()
//        }

        composable(route = RootDestinationScreen.Splash.route) {
            SplashScreen()
        }
    }

}

sealed class RootDestinationScreen(val route: String) {
    object Home : RootDestinationScreen(route = "HOME")
    object Splash : RootDestinationScreen(route = "SPLASH")
}

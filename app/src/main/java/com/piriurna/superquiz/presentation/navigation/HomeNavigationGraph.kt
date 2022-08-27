package com.piriurna.superquiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.piriurna.superquiz.presentation.home.HomeScreen
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.playgames.PlayGamesScreen
import com.piriurna.superquiz.presentation.questions.QuestionsScreen
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
            PlayGamesScreen(navController)
        }

        composable(
            route = HomeDestinationScreen.CategoryQuestions.route + "/{categoryId}",
            arguments = listOf(navArgument("categoryId"){
                type = NavType.StringType
            })
        ) {
                val categoryId = it.arguments?.getString("categoryId")!!.toInt()
                QuestionsScreen(categoryId = categoryId)
        }


//        composable(route = RootDestinationScreen.Splash.route) {
//            SplashScreen(navController = navController)
//        }
    }

}

sealed class HomeDestinationScreen(val route: String, val arguments : String = "") {
    object PlayGames : HomeDestinationScreen(route = "PLAY_GAMES")
    object CategoryQuestions : HomeDestinationScreen(route = "QUESTIONS", arguments = "categoryId")
}

package com.piriurna.superquiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.piriurna.superquiz.presentation.chart.ChartScreen
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.playgames.PlayGamesScreen
import com.piriurna.superquiz.presentation.profile.ProfileScreen
import com.piriurna.superquiz.presentation.profile.questions.QuestionsSettingsScreen
import com.piriurna.superquiz.presentation.profile.user.UserSettingsScreen
import com.piriurna.superquiz.presentation.questions.QuestionsScreen

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

        composable(route = HomeDestinationScreen.Chart.route) {
            ChartScreen()
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


        composable(route = HomeDestinationScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = HomeDestinationScreen.QuestionSettings.route) {
            QuestionsSettingsScreen()
        }

        composable(route = HomeDestinationScreen.UserSettings.route) {
            UserSettingsScreen()
        }
    }

}

sealed class HomeDestinationScreen(val route: String, val arguments : String = "") {
    object PlayGames : HomeDestinationScreen(route = "PLAY_GAMES")
    object Chart : HomeDestinationScreen(route = "CHART")
    object Profile : HomeDestinationScreen(route = "PROFILE")
    object QuestionSettings : HomeDestinationScreen(route = "QUESTION_SETTINGS")
    object UserSettings : HomeDestinationScreen(route = "USER_SETTINGS")
    object CategoryQuestions : HomeDestinationScreen(route = "QUESTIONS", arguments = "categoryId")
}

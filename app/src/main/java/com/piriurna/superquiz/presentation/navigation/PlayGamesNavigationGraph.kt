package com.piriurna.superquiz.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.piriurna.common.composables.navigation.BaseDestinations
import com.piriurna.common.composables.navigation.CustomArguments
import com.piriurna.superquiz.presentation.information.SuccessScreen
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.playgames.PlayGamesScreen

fun NavGraphBuilder.playGamesNavigationGraph(navController: NavHostController) {

    navigation(
        route = Graph.PLAY_GAMES_GRAPH,
        startDestination = PlayGamesDestinations.PlayGames.route
    ) {

        composable(route = PlayGamesDestinations.PlayGames.route) {
            PlayGamesScreen(navController)
        }

        composable(
            route = PlayGamesDestinations.CategoryCompleted.fullRoute,
            arguments = PlayGamesDestinations.CategoryCompleted.arguments
        ) {
            SuccessScreen(it)
        }


    }

}

sealed class PlayGamesDestinations(
    val route: String,
    customArguments: List<CustomArguments> = emptyList()
) : BaseDestinations(route = route, customArguments = customArguments) {
    object PlayGames : PlayGamesDestinations(route = "PLAY_GAMES")
    object CategoryCompleted : PlayGamesDestinations(route = "CATEGORY_COMPLETED", listOf(CustomArguments(NavigationArguments.CATEGORY_ID)))
}

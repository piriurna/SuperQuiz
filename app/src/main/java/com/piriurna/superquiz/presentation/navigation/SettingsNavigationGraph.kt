package com.piriurna.superquiz.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.piriurna.common.composables.navigation.BaseDestinations
import com.piriurna.common.composables.navigation.CustomArguments
import com.piriurna.superquiz.presentation.navigation.models.Graph
import com.piriurna.superquiz.presentation.profile.questions.QuestionsSettingsScreen
import com.piriurna.superquiz.presentation.profile.user.UserSettingsScreen

fun NavGraphBuilder.settingsNavigationGraph(navController: NavHostController) {

    navigation(
        route = Graph.SETTINGS_GRAPH,
        startDestination = SettingsDestinations.QuestionSettings.route
    ) {

        composable(route = SettingsDestinations.QuestionSettings.route) {
            QuestionsSettingsScreen()
        }

        composable(route = SettingsDestinations.UserSettings.route) {
            UserSettingsScreen(navController)
        }


    }

}

sealed class SettingsDestinations(
    val route: String,
    customArguments: List<CustomArguments> = emptyList()
) : BaseDestinations(route = route, customArguments = customArguments) {
    object QuestionSettings : PlayGamesDestinations(route = "QUESTION_SETTINGS")
    object UserSettings : PlayGamesDestinations(route = "USER_SETTINGS")
}

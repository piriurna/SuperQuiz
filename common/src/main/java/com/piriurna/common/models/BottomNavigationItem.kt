package com.piriurna.common.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val route : String,
    val icon : ImageVector,
    val title : String
) {

    object PlayGames : BottomNavigationItem(
        route = "play_games",
        title = "Play Games",
        icon = Icons.Default.PlayArrow
    )

    object Profile : BottomNavigationItem(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Default.Face
    )

    object Chart : BottomNavigationItem(
        route = "CHART",
        title = "Statistics",
        icon = Icons.Default.Favorite
    )

    companion object {
        val mockBottomNavigationItems = listOf(
            PlayGames,
            PlayGames
        )
    }

}

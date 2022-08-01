package com.piriurna.superquiz.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
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
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Face
    )

    companion object {
        val mockBottomNavigationItems = listOf(
            PlayGames,
            Profile
        )
    }

}

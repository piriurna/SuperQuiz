package com.piriurna.common.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.piriurna.common.R
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.progressBlue
import com.piriurna.common.theme.purple

sealed class BottomNavigationItem(
    val route : String,
    val iconRes : Int,
    val title : String,
    val color : Color = purple
) {

    object PlayGames : BottomNavigationItem(
        route = "PLAY_GAMES",
        title = "Play Games",
        iconRes = R.drawable.ic_play,
        color = progressBlue
    )

    object Profile : BottomNavigationItem(
        route = "PROFILE",
        title = "Profile",
        iconRes = R.drawable.ic_settings,
        color = orange
    )

    object Chart : BottomNavigationItem(
        route = "CHART",
        title = "Statistics",
        iconRes = R.drawable.ic_pie_chart,
        color = purple
    )

    companion object {
        val mockBottomNavigationItems = listOf(
            PlayGames,
            PlayGames
        )
    }

}

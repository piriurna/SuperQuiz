package com.piriurna.common.models

import androidx.compose.ui.graphics.Color
import com.piriurna.common.R
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.progressBlue
import com.piriurna.common.theme.purple

open class BottomNavigationItem(
    val route : String,
    val iconRes : Int,
    val title : String,
    val color : Color = purple
) {

    companion object {
        val getMockNavigationItems = listOf(
            BottomNavigationItem(
                route = "PLAY_GAMES",
                title = "Play Games",
                iconRes = R.drawable.ic_play,
                color = progressBlue
            ),
            BottomNavigationItem(
                route = "PROFILE",
                title = "Profile",
                iconRes = R.drawable.ic_settings,
                color = orange
            ),
            BottomNavigationItem(
                route = "CHART",
                title = "Statistics",
                iconRes = R.drawable.ic_pie_chart,
                color = purple
            )
        )
    }
}

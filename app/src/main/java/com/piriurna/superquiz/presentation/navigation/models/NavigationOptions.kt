package com.piriurna.superquiz.presentation.navigation.models

import com.piriurna.common.R
import com.piriurna.common.models.BottomNavigationItem
import com.piriurna.common.theme.orange
import com.piriurna.common.theme.progressBlue
import com.piriurna.common.theme.purple

object NavigationOptions {

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

}
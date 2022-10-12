package com.piriurna.superquiz.presentation.playgames

import com.piriurna.superquiz.SQBaseEvent

sealed class PlayGamesEvents : SQBaseEvent() {
    object GetData: PlayGamesEvents()

    object RefreshCategories : PlayGamesEvents()
}

package com.piriurna.superquiz.presentation.playgames

import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayGamesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : SQBaseEventViewModel<PlayGamesEvents>(){

    init {
        onTriggerEvent(PlayGamesEvents.GetCategories)
    }

    override fun onTriggerEvent(event: PlayGamesEvents) {
        when(event) {
            is PlayGamesEvents.GetCategories -> {
                getCategories()
            }
        }
    }


    private fun getCategories() {

    }
}
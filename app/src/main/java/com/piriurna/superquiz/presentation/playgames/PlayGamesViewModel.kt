package com.piriurna.superquiz.presentation.playgames

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlayGamesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : SQBaseEventViewModel<PlayGamesEvents>(){

    private val _state: MutableState<PlayGamesState> = mutableStateOf(PlayGamesState())
    val state: State<PlayGamesState> = _state


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
        getCategoriesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        categories = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )

                }
            }
        }.launchIn(viewModelScope)
    }
}
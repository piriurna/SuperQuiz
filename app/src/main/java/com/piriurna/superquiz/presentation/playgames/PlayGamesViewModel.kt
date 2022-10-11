package com.piriurna.superquiz.presentation.playgames

import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.domain.usecases.GetProfileSettingsUseCase
import com.piriurna.domain.usecases.RefreshCategoriesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayGamesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProfileSettingsUseCase: GetProfileSettingsUseCase,
    private val refreshCategoriesUseCase: RefreshCategoriesUseCase
) : SQBaseEventViewModel<PlayGamesEvents>(){


    private val _state = MutableStateFlow<PlayGamesState>(PlayGamesState())
    val state: StateFlow<PlayGamesState> = _state


    init {
        onTriggerEvent(PlayGamesEvents.GetData)
    }

    override fun onTriggerEvent(event: PlayGamesEvents) {
        when(event) {
            is PlayGamesEvents.GetData -> {
                getUserData()
            }

            is PlayGamesEvents.RefreshCategories -> {
                refreshCategories()
            }
        }
    }

    private fun getUserData() {
        getProfileSettingsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {

                    _state.value = _state.value.copy(
                        userName = result.data?.userName?:""
                    )

                    getCategories()
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun refreshCategories() {
        refreshCategoriesUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isRefreshing = true
                    )
                }

                else -> {
                    _state.value = _state.value.copy(
                        isRefreshing = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories(){

        viewModelScope.launch {
            getCategoriesUseCase().collectLatest{

                _state.value = _state.value.copy(
                    categories = it,
                    isLoading = false
                )
            }
        }
    }

}
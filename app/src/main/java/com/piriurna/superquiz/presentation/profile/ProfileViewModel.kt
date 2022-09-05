package com.piriurna.superquiz.presentation.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.models.ProfileSettings
import com.piriurna.domain.usecases.GetProfileSettingsUseCase
import com.piriurna.domain.usecases.SaveProfileSettingsUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.presentation.playgames.PlayGamesEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileSettingsUseCase : GetProfileSettingsUseCase,
    private val saveProfileSettingsUseCase: SaveProfileSettingsUseCase
) : SQBaseEventViewModel<ProfileEvents>() {

    private val _state : MutableState<ProfileState> = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state


    init {
        onTriggerEvent(ProfileEvents.FetchSettings)
    }

    fun triggerSaveSettings(numberOfQuestions: Int) {
        onTriggerEvent(ProfileEvents.SaveSettings(_state.value.profileSettings.copy(
            numberOfQuestions = numberOfQuestions
        )))
    }

    override fun onTriggerEvent(event: ProfileEvents) {
        when(event) {
            is ProfileEvents.FetchSettings -> {
                fetchSettings()
            }

            is ProfileEvents.SaveSettings -> {
                saveSettings(event.profileSettings)
            }
        }
    }


    private fun fetchSettings() {
        getProfileSettingsUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        profileSettings = result.data?: ProfileSettings()
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        profileSettings = result.data?: ProfileSettings()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveSettings(profileSettings: ProfileSettings) {
        saveProfileSettingsUseCase(profileSettings).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
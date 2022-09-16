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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    private val getProfileSettingsUseCase : GetProfileSettingsUseCase,
    private val saveProfileSettingsUseCase: SaveProfileSettingsUseCase
) : SQBaseEventViewModel<ProfileSettingsEvents>() {

    private val _state : MutableState<ProfileSettingsState> = mutableStateOf(ProfileSettingsState())
    val state: State<ProfileSettingsState> = _state


    init {
        onTriggerEvent(ProfileSettingsEvents.FetchSettings)
    }

    fun triggerSaveNumberOfQuestions(numberOfQuestions: Int) {
        onTriggerEvent(
            ProfileSettingsEvents.SaveSettings(
                _state.value.profileSettings.copy(numberOfQuestions = numberOfQuestions)
            )
        )
    }

    fun triggerSaveUserName(userName: String) {
        onTriggerEvent(
            ProfileSettingsEvents.SaveSettings(
                _state.value.profileSettings.copy(userName = userName)
            )
        )
    }

    override fun onTriggerEvent(event: ProfileSettingsEvents) {
        when(event) {
            is ProfileSettingsEvents.FetchSettings -> {
                fetchSettings()
            }

            is ProfileSettingsEvents.SaveSettings -> {
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
                        profileSettings = result.data?:ProfileSettings()
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
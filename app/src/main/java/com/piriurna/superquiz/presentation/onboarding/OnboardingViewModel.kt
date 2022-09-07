package com.piriurna.superquiz.presentation.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetOnboardingPagesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.mappers.toOnboardingPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase
) : SQBaseEventViewModel<OnboardingEvents>() {


    private val _state: MutableState<OnboardingState> = mutableStateOf(OnboardingState())
    val state: State<OnboardingState> = _state


    init {
        onTriggerEvent(OnboardingEvents.FetchOnboardingPages)
    }


    override fun onTriggerEvent(event: OnboardingEvents) {
        when(event) {
            is OnboardingEvents.FetchOnboardingPages -> {
                fetchOnboardingPages()
            }
        }
    }


    private fun fetchOnboardingPages() {
        getOnboardingPagesUseCase().onEach { result ->

            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        onboardingPages = result.data?.toOnboardingPage()?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
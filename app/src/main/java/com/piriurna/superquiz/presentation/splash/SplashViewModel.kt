package com.piriurna.superquiz.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.LoadTriviaDataUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loadTriviaDataUseCase: LoadTriviaDataUseCase
) : SQBaseEventViewModel<SplashEvents>(){

    private val _state: MutableState<SplashState> = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    override fun onTriggerEvent(event: SplashEvents) {
        when(event) {
            is SplashEvents.LoadTriviaData -> {
                loadTriviaData()
            }
        }
    }


    private fun loadTriviaData() {
        loadTriviaDataUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        success = result.data == true
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
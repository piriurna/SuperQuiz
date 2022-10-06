package com.piriurna.superquiz.presentation.chart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : SQBaseEventViewModel<ChartEvents>(){

    private val _state: MutableState<ChartState> = mutableStateOf(ChartState())
    val state: State<ChartState> = _state

    init {
        onTriggerEvent(ChartEvents.GetCategoryStatistics)
    }

    override fun onTriggerEvent(event: ChartEvents) {
        when(event) {
            is ChartEvents.GetCategoryStatistics -> {
                getAnswersForCategory()
            }
        }
    }

    private fun getAnswersForCategory() {
        getCategoriesUseCase().onEach { result ->

            _state.value = _state.value.copy(
                categories = result,
                isLoading = false
            )

        }.launchIn(viewModelScope)
    }
}
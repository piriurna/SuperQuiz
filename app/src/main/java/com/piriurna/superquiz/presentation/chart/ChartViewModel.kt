package com.piriurna.superquiz.presentation.chart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.domain.usecases.GetCategoryStatisticsUseCase
import com.piriurna.domain.usecases.chart.GetCategoryStatisticsForAllCategoriesUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getCategoryStatisticsForAllCategoriesUseCase: GetCategoryStatisticsForAllCategoriesUseCase,
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
        getCategoryStatisticsForAllCategoriesUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        categoryStatisticsList = result.data?: emptyList(),
                        isLoading = false
                    )
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}
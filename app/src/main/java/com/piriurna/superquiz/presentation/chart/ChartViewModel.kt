package com.piriurna.superquiz.presentation.chart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetQuestionsStatisticsForCategory
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getQuestionsStatisticsForCategory: GetQuestionsStatisticsForCategory
) : SQBaseEventViewModel<ChartEvents>(){

    private val _state: MutableState<ChartState> = mutableStateOf(ChartState())
    val state: State<ChartState> = _state

    init {
        onTriggerEvent(ChartEvents.GetAnswersForCategory(9))
    }

    override fun onTriggerEvent(event: ChartEvents) {
        when(event) {
            is ChartEvents.GetAnswersForCategory -> {
                getAnswersForCategory(event.categoryId)
            }
        }
    }

    private fun getAnswersForCategory(categoryId : Int) {
        getQuestionsStatisticsForCategory.invoke(categoryId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        questions = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                else -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}
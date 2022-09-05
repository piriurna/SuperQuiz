package com.piriurna.superquiz.presentation.chart

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetCategoriesUseCase
import com.piriurna.domain.usecases.GetQuestionsStatisticsForCategoryUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getQuestionsStatisticsForCategoryUseCase: GetQuestionsStatisticsForCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : SQBaseEventViewModel<ChartEvents>(){

    private val _state: MutableState<ChartState> = mutableStateOf(ChartState())
    val state: State<ChartState> = _state

    init {
        onTriggerEvent(ChartEvents.GetCategories)
    }

    override fun onTriggerEvent(event: ChartEvents) {
        when(event) {
            is ChartEvents.GetAnswersForCategory -> {
                getAnswersForCategory(event.categoryId)
            }

            is ChartEvents.GetCategories -> {
                getCategories()
            }
        }
    }

    private fun getAnswersForCategory(categoryId : Int) {
        getQuestionsStatisticsForCategoryUseCase.invoke(categoryId).onEach { result ->
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

    private fun getCategories() {
        getCategoriesUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        categories = result.data ?: emptyList(),
                        isLoading = false
                    )

                    onTriggerEvent(ChartEvents.GetAnswersForCategory(result.data!![0].id))
                }

                else -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    fun setSelectedCategory(categoryId: String) {
        onTriggerEvent(ChartEvents.GetAnswersForCategory(categoryId.toInt()))
    }
}
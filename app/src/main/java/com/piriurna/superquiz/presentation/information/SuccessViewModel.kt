package com.piriurna.superquiz.presentation.information

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.piriurna.domain.Resource
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.usecases.GetCategoryStatisticsUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.presentation.information.models.SuccessEvents
import com.piriurna.superquiz.presentation.information.models.SuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(
    private val getCategoryStatisticsUseCase: GetCategoryStatisticsUseCase
) : SQBaseEventViewModel<SuccessEvents>(){

    private val _state: MutableState<SuccessState> = mutableStateOf(SuccessState())
    val state: State<SuccessState> = _state



    override fun onTriggerEvent(event: SuccessEvents) {
        when(event) {
            is SuccessEvents.GetCategoryStatistics -> {
                getCategoryStatistics(categoryId = event.categoryId)
            }
        }
    }


    private fun getCategoryStatistics(categoryId : Int) {
        getCategoryStatisticsUseCase(categoryId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        categoryStatistics = result.data?: CategoryStatistics()
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }

        }

    }
}
package com.piriurna.superquiz.presentation.information.categories.end

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.usecases.GetCategoryStatisticsUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndEvents
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryEndViewModel @Inject constructor(
    private val getCategoryStatisticsUseCase: GetCategoryStatisticsUseCase
) : SQBaseEventViewModel<CategoryEndEvents>(){

    private val _state: MutableState<CategoryEndState> = mutableStateOf(CategoryEndState())
    val state: State<CategoryEndState> = _state



    override fun onTriggerEvent(event: CategoryEndEvents) {
        when(event) {
            is CategoryEndEvents.GetCategoryStatistics -> {
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

        }.launchIn(viewModelScope)

    }
}
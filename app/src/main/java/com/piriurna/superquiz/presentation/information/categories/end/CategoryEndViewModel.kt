package com.piriurna.superquiz.presentation.information.categories.end

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.usecases.GetCategoryUseCase
import com.piriurna.domain.usecases.questions.FetchQuestionsForCategoryUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndDestination
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndEvents
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryEndViewModel @Inject constructor(
    private val getCategoryStatisticsUseCase: GetCategoryUseCase,
    private val fetchQuestionsForCategoryUseCase: FetchQuestionsForCategoryUseCase
) : SQBaseEventViewModel<CategoryEndEvents>(){

    private val _state: MutableState<CategoryEndState> = mutableStateOf(CategoryEndState())
    val state: State<CategoryEndState> = _state



    override fun onTriggerEvent(event: CategoryEndEvents) {
        when(event) {
            is CategoryEndEvents.GetCategoryStatistics -> {
                getCategoryStatistics(categoryId = event.categoryId)
            }

            is CategoryEndEvents.FetchMoreQuestions -> {

            }
        }
    }


    private fun getCategoryStatistics(categoryId : Int) {
        viewModelScope.launch {
            getCategoryStatisticsUseCase(categoryId).onEach { result ->

                    _state.value = _state.value.copy(
                        isLoading = false,
                        category = result
                    )
            }

        }
    }

    private fun fetchMoreQuestions(categoryId: Int) {
        viewModelScope.launch {
            fetchQuestionsForCategoryUseCase(categoryId = categoryId).onEach {
                when(it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            destination = CategoryEndDestination.GO_TO_QUESTIONS
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}
package com.piriurna.superquiz.presentation.information.categories.end

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.piriurna.data.remote.ErrorType
import com.piriurna.domain.Resource
import com.piriurna.domain.models.CategoryStatistics
import com.piriurna.domain.usecases.GetCategoryUseCase
import com.piriurna.domain.usecases.questions.FetchQuestionsForCategoryUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.mappers.toSQError
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndDestination
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndEvents
import com.piriurna.superquiz.presentation.information.categories.end.models.CategoryEndState
import com.piriurna.superquiz.presentation.navigation.NavigationArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryEndViewModel @Inject constructor(
    private val getCategoryStatisticsUseCase: GetCategoryUseCase,
    private val fetchQuestionsForCategoryUseCase: FetchQuestionsForCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : SQBaseEventViewModel<CategoryEndEvents>(){

    private val _state: MutableState<CategoryEndState> = mutableStateOf(CategoryEndState())
    val state: State<CategoryEndState> = _state


    init {
        val categoryId = savedStateHandle.get<Int>(NavigationArguments.CATEGORY_ID)?:0

        onTriggerEvent(CategoryEndEvents.GetCategoryStatistics(categoryId))
    }

    override fun onTriggerEvent(event: CategoryEndEvents) {
        when(event) {
            is CategoryEndEvents.GetCategoryStatistics -> {
                getCategoryStatistics(categoryId = event.categoryId)
            }

            is CategoryEndEvents.FetchMoreQuestions -> {
                _state.value.category?.let {
                    fetchMoreQuestions(it.id)
                }
            }
        }
    }


    private fun getCategoryStatistics(categoryId : Int) {
        viewModelScope.launch {
            getCategoryStatisticsUseCase(categoryId).collectLatest { result ->

                    _state.value = _state.value.copy(
                        isLoading = false,
                        category = result
                    )
            }

        }
    }

    private fun fetchMoreQuestions(categoryId: Int) {
        fetchQuestionsForCategoryUseCase(categoryId = categoryId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        destination = CategoryEndDestination.GO_TO_QUESTIONS
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = null
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = ErrorType.valueFromCode(result.code).toSQError { CategoryEndEvents.FetchMoreQuestions }
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.piriurna.superquiz.presentation.questions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.piriurna.domain.Resource
import com.piriurna.domain.usecases.GetCategoryQuestionsUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getCategoryQuestionsUseCase: GetCategoryQuestionsUseCase
) : SQBaseEventViewModel<QuestionsEvents>(){


    private val _state: MutableState<QuestionsState> = mutableStateOf(QuestionsState())
    val state: State<QuestionsState> = _state


    init {
        onTriggerEvent(QuestionsEvents.GetQuestions(9))
    }

    override fun onTriggerEvent(event: QuestionsEvents) {
        when(event) {
            is QuestionsEvents.GetQuestions -> {
                getQuestions(event.categoryId)
            }
        }
    }


    private fun getQuestions(categoryId: Int) {
        getCategoryQuestionsUseCase(categoryId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        questions = result.data?: emptyList()
                    )
                }
            }
        }
    }
}
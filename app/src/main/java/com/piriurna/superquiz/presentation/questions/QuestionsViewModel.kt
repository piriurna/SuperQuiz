package com.piriurna.superquiz.presentation.questions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.models.questions.CategoryInformation
import com.piriurna.domain.usecases.GetCategoryQuestionsUseCase
import com.piriurna.domain.usecases.SaveAnswerUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getCategoryQuestionsUseCase: GetCategoryQuestionsUseCase,
    private val saveAnswerUseCase: SaveAnswerUseCase
) : SQBaseEventViewModel<QuestionsEvents>(){


    private val _state: MutableState<QuestionsState> = mutableStateOf(QuestionsState())
    val state: State<QuestionsState> = _state

    override fun onTriggerEvent(event: QuestionsEvents) {
        when(event) {
            is QuestionsEvents.GetQuestions -> {
                getQuestions(event.categoryId)
            }

            is QuestionsEvents.SaveAnswer -> {
                saveAnswer(event.questionId, event.answer)
            }

            is QuestionsEvents.PerformHintAction -> {

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
                        categoryInformation = result.data?: CategoryInformation()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun saveAnswer(questionId: Int, answer: Answer) {
        saveAnswerUseCase.invoke(questionId, answer).onEach { result ->
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
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun performHint(question: Question) {

    }
}
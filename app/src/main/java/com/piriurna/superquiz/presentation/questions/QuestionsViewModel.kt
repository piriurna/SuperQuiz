package com.piriurna.superquiz.presentation.questions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Answer
import com.piriurna.domain.models.Question
import com.piriurna.domain.usecases.GetDbCategoryQuestionsUseCase
import com.piriurna.domain.usecases.SaveAnswerUseCase
import com.piriurna.domain.usecases.questions.DisableSelectedAnswersUseCase
import com.piriurna.domain.usecases.questions.FetchQuestionsForCategoryUseCase
import com.piriurna.domain.usecases.quotes.GetRandomQuoteListUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val getDbCategoryQuestionsUseCase: GetDbCategoryQuestionsUseCase,
    private val saveAnswerUseCase: SaveAnswerUseCase,
    private val disableSelectedAnswersUseCase: DisableSelectedAnswersUseCase,
    private val fetchQuestionsForCategoryUseCase: FetchQuestionsForCategoryUseCase,
    private val getRandomQuoteListUseCase: GetRandomQuoteListUseCase
) : SQBaseEventViewModel<QuestionsEvents>(){


    private val _state: MutableState<QuestionsState> = mutableStateOf(QuestionsState())
    val state: State<QuestionsState> = _state


    private val _allTasks = MutableStateFlow<QuestionsState>(QuestionsState())
    val allTasks: StateFlow<QuestionsState> = _allTasks

    private var fetchQuotes = true


    override fun onTriggerEvent(event: QuestionsEvents) {
        when(event) {
            is QuestionsEvents.GetQuestions -> {
                getQuestions(event.categoryId)
            }

            is QuestionsEvents.SaveAnswer -> {
                saveAnswer(event.questionId, event.answer)
            }

            is QuestionsEvents.PerformHintAction -> {
                performHint(event.question)
            }

            is QuestionsEvents.FetchQuestionsForCategory -> {
                fetchCategoryQuestions(event.categoryId)
            }
        }
    }


    private fun getQuestions(categoryId: Int){

        viewModelScope.launch {
            getDbCategoryQuestionsUseCase(categoryId).collectLatest{ questions->
                _allTasks.value = _allTasks.value.copy(
                    categoryQuestions = questions,
                    lastAnsweredQuestionId = questions.firstOrNull { !it.isQuestionAnswered() }?.id?:0,
                    categoryId = categoryId,
                    isLoading = false
                )

                getQuotes(_allTasks.value.categoryQuestions.size)
            }
        }



    }



    private fun getQuotes(numOfQuotes : Int) {

        if(!fetchQuotes) return

        getRandomQuoteListUseCase(numOfQuotes).onEach { result ->
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
                        quotes = result.data?: emptyList(),
                    )
                    fetchQuotes = false
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
        disableSelectedAnswersUseCase(question).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    getQuestions(question.categoryId)
                }
                else -> {}
            }


        }.launchIn(viewModelScope)
    }


    private fun fetchCategoryQuestions(categoryId : Int) {
        fetchQuestionsForCategoryUseCase(categoryId).onEach { result ->
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
                    getQuestions(categoryId)
                }
            }
        }.launchIn(viewModelScope)
    }
}
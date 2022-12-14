package com.piriurna.superquiz.presentation.questions

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.piriurna.data.remote.ErrorType
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Question
import com.piriurna.domain.usecases.GetCategoryUseCase
import com.piriurna.domain.usecases.GetDbCategoryQuestionsUseCase
import com.piriurna.domain.usecases.SaveAnswerUseCase
import com.piriurna.domain.usecases.questions.DisableSelectedAnswersUseCase
import com.piriurna.domain.usecases.questions.FetchQuestionsForCategoryUseCase
import com.piriurna.domain.usecases.quotes.GetRandomQuoteListUseCase
import com.piriurna.superquiz.SQBaseEventViewModel
import com.piriurna.superquiz.mappers.toSQError
import com.piriurna.superquiz.presentation.navigation.NavigationArguments
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
    private val getRandomQuoteListUseCase: GetRandomQuoteListUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : SQBaseEventViewModel<QuestionsEvents>(){


    private val _state: MutableStateFlow<QuestionsState> = MutableStateFlow(QuestionsState())
    val state: StateFlow<QuestionsState> = _state

    init {
        val categoryId = savedStateHandle.get<Int>(NavigationArguments.CATEGORY_ID)?:0

        onTriggerEvent(QuestionsEvents.GetQuestions(categoryId))
        onTriggerEvent(QuestionsEvents.GetCategory(categoryId))
    }

    override fun onTriggerEvent(event: QuestionsEvents) {
        when(event) {

            is QuestionsEvents.GetCategory -> {
                getCategory(event.categoryId)
            }

            is QuestionsEvents.GetQuestions -> {
                getQuestions(event.categoryId)
            }

            is QuestionsEvents.PerformHintAction -> {
                performHint(event.question)
            }

            is QuestionsEvents.FetchQuestionsForCategory -> {
                fetchCategoryQuestions(event.categoryId)
            }

            is QuestionsEvents.ShowResult -> {
                _state.value = _state.value.copy(
                    destination = QuestionDestination.SHOW_ANSWER_RESULT
                )
            }

            is QuestionsEvents.GoToNextPage -> {

                _state.value.currentQuestion?.let { currentQuestion ->
                    saveAnswer(currentQuestion)

                    if(_state.value.isLastQuestion()){
                        _state.value = _state.value.copy(
                            destination = QuestionDestination.GO_TO_RESULTS
                        )
                    } else {
                        _state.value = _state.value.copy(
                            destination = QuestionDestination.SHOW_QUESTION,
                            currentQuestion = _state.value.getNextQuestion(),
                        )
                    }
                }



            }

            is QuestionsEvents.SelectAnswer -> {
                val currentQuestion = _state.value.currentQuestion?.copy(
                    chosenAnswer = event.answer
                )
                _state.value = _state.value.copy(
                    currentQuestion = currentQuestion
                )
            }

            is QuestionsEvents.DismissNoQuestionsPopup -> {
                _state.value = _state.value.copy(
                    destination = QuestionDestination.QUIT
                )
            }
        }
    }


    private fun getQuestions(categoryId: Int) {
        viewModelScope.launch {
            val questionsList = getDbCategoryQuestionsUseCase(categoryId).first()


            val currentQuestion = questionsList.firstOrNull { !it.isQuestionAnswered() }

            _state.value = _state.value.copy(
                questionsList = questionsList,
                currentQuestion = currentQuestion,
                destination = if(currentQuestion == null) QuestionDestination.NO_QUESTIONS_AVAILABLE else QuestionDestination.SHOW_QUESTION
            )

            getQuotes(questionsList.size)



        }
    }

    private fun getQuotes(numOfQuotes : Int) {
        getRandomQuoteListUseCase(numOfQuotes).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorType.valueFromCode(result.code).toSQError { getQuotes(numOfQuotes) }
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        quotes = result.data?: emptyList(),
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveAnswer(question : Question) {
        saveAnswerUseCase.invoke(question, question.chosenAnswer!!).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = ErrorType.valueFromCode(result.code).toSQError { saveAnswer(question) }
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
                    _state.value = _state.value.copy(
                        currentQuestion = result.data,
                    )
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
                        isLoading = false,
                        error = ErrorType.valueFromCode(result.code).toSQError { QuestionsEvents.FetchQuestionsForCategory(categoryId) }
                    )
                }

                is Resource.Success -> {
                    getQuestions(categoryId)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategory(categoryId: Int) {
        viewModelScope.launch {
            getCategoryUseCase(categoryId).collectLatest {
                _state.value = _state.value.copy(
                    category = it
                )
            }
        }
    }
}
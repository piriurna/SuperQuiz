package com.piriurna.superquiz.presentation.splash

import com.piriurna.domain.models.LoadTriviaType

data class SplashState(
    val isLoading : Boolean = false,
    val loadTriviaState: LoadTriviaType = LoadTriviaType.UNDEFINED
)
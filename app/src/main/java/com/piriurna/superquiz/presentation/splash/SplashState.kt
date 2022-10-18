package com.piriurna.superquiz.presentation.splash

import com.piriurna.domain.models.splash.SplashDestination
import com.piriurna.superquiz.presentation.splash.models.SplashError

data class SplashState(
    val isLoading : Boolean = false,
    val destination: SplashDestination = SplashDestination.UNDEFINED,
    val error: SplashError? = null
)
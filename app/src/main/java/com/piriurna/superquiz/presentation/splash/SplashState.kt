package com.piriurna.superquiz.presentation.splash

import com.piriurna.domain.models.splash.SplashDestination

data class SplashState(
    val isLoading : Boolean = false,
    val destination: SplashDestination = SplashDestination.UNDEFINED
)
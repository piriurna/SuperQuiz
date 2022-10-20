package com.piriurna.superquiz.presentation.splash

import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.superquiz.SQBaseEvent

sealed class SplashEvents : SQBaseEvent(){
    object LoadTriviaData: SplashEvents()

    object Retry : SplashEvents()
}
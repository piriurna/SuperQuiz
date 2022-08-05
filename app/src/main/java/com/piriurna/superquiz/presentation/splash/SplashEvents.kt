package com.piriurna.superquiz.presentation.splash

import com.piriurna.superquiz.SQBaseEvent

sealed class SplashEvents : SQBaseEvent(){
    object LoadTriviaData: SplashEvents()
}
package com.piriurna.superquiz.presentation.onboarding

import com.piriurna.superquiz.SQBaseEvent

sealed class OnboardingEvents : SQBaseEvent(){

    object FetchOnboardingPages : OnboardingEvents()
}

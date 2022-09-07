package com.piriurna.superquiz.presentation.onboarding

import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage

data class OnboardingState(
    val isLoading : Boolean = false,
    val onboardingPages: List<OnboardingPage> = emptyList()
)

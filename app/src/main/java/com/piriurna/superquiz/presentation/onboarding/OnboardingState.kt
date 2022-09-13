package com.piriurna.superquiz.presentation.onboarding

import com.piriurna.superquiz.presentation.onboarding.models.OnboardingUI

data class OnboardingState(
    val isLoading : Boolean = false,
    val onboardingPages: List<OnboardingUI> = emptyList()
)

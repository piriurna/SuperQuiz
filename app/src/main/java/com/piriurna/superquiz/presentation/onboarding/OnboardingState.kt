package com.piriurna.superquiz.presentation.onboarding

import com.piriurna.common.models.SQError
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingUI

data class OnboardingState(
    val isLoading : Boolean = false,
    val onboardingPages: List<OnboardingUI> = emptyList(),
    val error : SQError? = null
)

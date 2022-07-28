package com.piriurna.superquiz.presentation.onboarding.models

import androidx.compose.ui.graphics.Color

data class OnboardingPageModel(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageIndex : Int,
    val pageTitle : String,
    val pageDescription : String
)
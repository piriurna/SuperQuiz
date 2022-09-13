package com.piriurna.superquiz.presentation.onboarding.models

import androidx.compose.ui.graphics.Color
import com.piriurna.domain.models.Onboarding
import com.piriurna.superquiz.mappers.toOnboardingUI

data class OnboardingUI(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageTitle : String,
    val pageDescription : String,
    val mainImage : Int = 0,
    val mainImageUrl : String = ""
) {

    companion object {
        val getOnboardingMockList = Onboarding.getOnboardingMockList.toOnboardingUI()
    }
}
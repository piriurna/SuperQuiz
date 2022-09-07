package com.piriurna.superquiz.mappers

import androidx.compose.ui.graphics.Color
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage


fun com.piriurna.domain.models.OnboardingPage.toOnboardingPage() : OnboardingPage {

    return OnboardingPage(
        pageTitle = this.pageTitle,
        pageDescription = this.pageDescription,
        primaryColor = Color(red = this.primaryColor.red, blue = this.primaryColor.blue, green = this.primaryColor.green, alpha = this.primaryColor.alpha),
        backgroundColor = Color(red = this.backgroundColor.red, blue = this.backgroundColor.blue, green = this.backgroundColor.green, alpha = this.backgroundColor.alpha),
        mainImageUrl = this.mainImage
    )
}
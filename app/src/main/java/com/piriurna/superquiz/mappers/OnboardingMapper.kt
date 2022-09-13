package com.piriurna.superquiz.mappers

import androidx.compose.ui.graphics.Color
import com.piriurna.domain.models.Onboarding
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingImage
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingUI


fun Onboarding.toOnboardingUI() : OnboardingUI {

    return OnboardingUI(
        pageTitle = this.pageTitle,
        pageDescription = this.pageDescription,
        primaryColor = Color(red = this.primaryColorPalette.red, blue = this.primaryColorPalette.blue, green = this.primaryColorPalette.green, alpha = this.primaryColorPalette.alpha),
        backgroundColor = Color(red = this.backgroundColorPalette.red, blue = this.backgroundColorPalette.blue, green = this.backgroundColorPalette.green, alpha = this.backgroundColorPalette.alpha),
        mainImage = OnboardingImage.getById(this.id).image,
        mainImageUrl = this.mainImageUrl
    )
}

fun List<Onboarding>.toOnboardingUI() : List<OnboardingUI> {
    return this.map {
        it.toOnboardingUI()
    }
}
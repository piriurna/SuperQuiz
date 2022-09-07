package com.piriurna.superquiz.presentation.onboarding.models

import androidx.compose.ui.graphics.Color
import com.piriurna.superquiz.ui.theme.*

data class OnboardingPage(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageTitle : String,
    val pageDescription : String,
    val mainImageUrl : String
) {



    companion object {
        val getOnboardingMockList = listOf(
            OnboardingPage(
                pageTitle = "Hmmmm, Healthy food",
                pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
                primaryColor = orange,
                backgroundColor = lightOrange,
                mainImageUrl = "https://www.svgrepo.com/svg/286031/lime"
            ),
            OnboardingPage(
                pageTitle = "Fresh Drinks, Stay Fresh",
                pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
                primaryColor = primaryBlue,
                backgroundColor = backgroundBlue,
                mainImageUrl = "https://www.svgrepo.com/show/98610/strawberry.svg"
            ),
            OnboardingPage(
                pageTitle = "Let's Cooking",
                pageDescription = "Are you ready to make a dish for your friends or family? create an account and cook",
                primaryColor = primaryGreen,
                backgroundColor = backgroundGreen,
                mainImageUrl = "https://www.svgrepo.com/svg/286031/lime"
            )
        )
    }
}
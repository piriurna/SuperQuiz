package com.piriurna.superquiz.presentation.onboarding.models

import androidx.compose.ui.graphics.Color
import com.piriurna.superquiz.R
import com.piriurna.superquiz.ui.theme.*

data class OnboardingPage(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageTitle : String,
    val pageDescription : String,
    val mainImage : Int = 0
) {



    companion object {
        val getOnboardingMockList = listOf(
            OnboardingPage(
                pageTitle = "Hmmmm, Healthy food",
                pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
                primaryColor = orange,
                backgroundColor = lightOrange,
                mainImage = R.drawable.ic_banana_svgrepo_com
            ),
            OnboardingPage(
                pageTitle = "Fresh Drinks, Stay Fresh",
                pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
                primaryColor = primaryBlue,
                backgroundColor = backgroundBlue,
                mainImage = R.drawable.ic_strawberries
            ),
            OnboardingPage(
                pageTitle = "Let's Cooking",
                pageDescription = "Are you ready to make a dish for your friends or family? create an account and cook",
                primaryColor = primaryGreen,
                backgroundColor = backgroundGreen,
                mainImage = R.drawable.ic_lime
            )
        )
    }
}
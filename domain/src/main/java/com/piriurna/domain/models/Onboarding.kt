package com.piriurna.domain.models


data class Onboarding(
    val id : String,
    val primaryColorPalette : ColorPalette,
    val backgroundColorPalette : ColorPalette,
    val pageTitle : String,
    val pageDescription : String,
    val mainImageUrl : String
){


    companion object {
        val getOnboardingMockList = listOf(
            Onboarding(
                id = "banana",
                pageTitle = "Hmmmm, Healthy food",
                pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
                primaryColorPalette = ColorPalette(254, 177,87),
                backgroundColorPalette = ColorPalette(254, 177,87, 26),
                mainImageUrl = "https://www.svgrepo.com/show/98610/strawberry.svg"
            ),
            Onboarding(
                id = "strawberry",
                pageTitle = "Fresh Drinks, Stay Fresh",
                pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
                primaryColorPalette = ColorPalette(red = 47, green = 204, blue = 237),
                backgroundColorPalette = ColorPalette(red = 172, green = 232, blue = 243),
                mainImageUrl = "https://www.svgrepo.com/show/286031/lime.svg"
            ),
            Onboarding(
                id = "lime",
                pageTitle = "Let's Cooking",
                pageDescription = "Are you ready to make a dish for your friends or family? create an account and cook",
                primaryColorPalette = ColorPalette(54, 200, 133),
                backgroundColorPalette = ColorPalette(54, 200, 133, 26),
                mainImageUrl = "https://www.svgrepo.com/show/56657/banana.svg"
            )
        )
    }
}
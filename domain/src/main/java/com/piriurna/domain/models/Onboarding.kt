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
                pageTitle = "Expand your knowledge!",
                pageDescription = "Never ending questions for a never ending thirst of knowledge",
                primaryColorPalette = ColorPalette(254, 177,87),
                backgroundColorPalette = ColorPalette(254, 177,87, 26),
                mainImageUrl = "https://www.svgrepo.com/show/98610/strawberry.svg"
            ),
            Onboarding(
                id = "strawberry",
                pageTitle = "Stronger Brain!",
                pageDescription = "Don't forget to stimulate your brain everyday",
                primaryColorPalette = ColorPalette(red = 47, green = 204, blue = 237),
                backgroundColorPalette = ColorPalette(red = 172, green = 232, blue = 243),
                mainImageUrl = "https://www.svgrepo.com/show/286031/lime.svg"
            ),
            Onboarding(
                id = "lime",
                pageTitle = "Fight!",
                pageDescription = "There is no knowledge that is not power - Ed Boon - Mortal Kombat Creator",
                primaryColorPalette = ColorPalette(54, 200, 133),
                backgroundColorPalette = ColorPalette(54, 200, 133, 26),
                mainImageUrl = "https://www.svgrepo.com/show/56657/banana.svg"
            )
        )
    }
}
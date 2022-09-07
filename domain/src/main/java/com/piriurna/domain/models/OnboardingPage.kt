package com.piriurna.domain.models


data class OnboardingPage(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageTitle : String,
    val pageDescription : String,
    val mainImage : String = ""
){


    companion object {
        val getOnboardingMockList = listOf(
            OnboardingPage(
                pageTitle = "Hmmmm, Healthy food",
                pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
                primaryColor = Color(254, 177,87),
                backgroundColor = Color(254, 177,87, 26),
                mainImage = "https://www.svgrepo.com/show/56657/banana.svg"
            ),
            OnboardingPage(
                pageTitle = "Fresh Drinks, Stay Fresh",
                pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
                primaryColor = Color(54, 200, 133),
                backgroundColor = Color(54, 200, 133, 26),
                mainImage = "https://www.svgrepo.com/show/98610/strawberry.svg"
            ),
            OnboardingPage(
                pageTitle = "Let's Cooking",
                pageDescription = "Are you ready to make a dish for your friends or family? create an account and cook",
                primaryColor = Color(54, 200, 133),
                backgroundColor = Color(54, 200, 133, 26),
                mainImage = "https://www.svgrepo.com/show/286031/lime.svg"
            )
        )
    }
}
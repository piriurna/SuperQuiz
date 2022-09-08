package com.piriurna.domain.models


data class OnboardingPage(
    val id : String,
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageTitle : String,
    val pageDescription : String,
    val mainImageUrl : String
){


    companion object {
        val getOnboardingMockList = listOf(
            OnboardingPage(
                id = "banana",
                pageTitle = "Hmmmm, Healthy food",
                pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
                primaryColor = Color(254, 177,87),
                backgroundColor = Color(254, 177,87, 26),
                mainImageUrl = "https://www.svgrepo.com/show/56657/banana.svg"
            ),
            OnboardingPage(
                id = "strawberry",
                pageTitle = "Fresh Drinks, Stay Fresh",
                pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
                primaryColor = Color(red = 47, green = 204, blue = 237),
                backgroundColor = Color(red = 172, green = 232, blue = 243),
                mainImageUrl = "https://www.svgrepo.com/show/98610/strawberry.svg"
            ),
            OnboardingPage(
                id = "lime",
                pageTitle = "Let's Cooking",
                pageDescription = "Are you ready to make a dish for your friends or family? create an account and cook",
                primaryColor = Color(54, 200, 133),
                backgroundColor = Color(54, 200, 133, 26),
                mainImageUrl = "https://www.svgrepo.com/show/286031/lime.svg"
            )
        )
    }
}
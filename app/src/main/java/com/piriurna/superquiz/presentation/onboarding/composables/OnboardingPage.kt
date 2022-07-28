package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPageModel
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange

@Composable
fun OnboardingPage(
    onboardingPageModel: OnboardingPageModel
) {
    Box(
        modifier = Modifier
            .background(onboardingPageModel.backgroundColor)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = onboardingPageModel.mainImage),
            contentDescription = "Page Image",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 36.dp)
                .padding(top = 36.dp)
        )
        OnboardingCard(
            onboardingPageModel = onboardingPageModel,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingPagePreview() {
    val model = OnboardingPageModel(
        pageTitle = "Hmmmm, Healthy food",
        pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
        primaryColor = orange,
        backgroundColor = lightPurple,
        pageIndex = 5,
        pageCount = 6,
        mainImage = R.drawable.ic_banana_svgrepo_com
    )
    OnboardingPage(onboardingPageModel = model)
}
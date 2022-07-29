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
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage

@Composable
fun OnboardingPage(
    onboardingPage: OnboardingPage
) {
    Box(
        modifier = Modifier
            .background(onboardingPage.backgroundColor)
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = onboardingPage.mainImage),
            contentDescription = "Page Image",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 36.dp)
                .padding(top = 36.dp)
        )
        OnboardingCard(
            onboardingPage = onboardingPage,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingPagePreview() {
    OnboardingPage(OnboardingPage.getOnboardingMockList[0])
}
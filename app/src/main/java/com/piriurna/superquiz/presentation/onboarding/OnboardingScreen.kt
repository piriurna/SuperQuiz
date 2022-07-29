package com.piriurna.superquiz.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.superquiz.presentation.onboarding.composables.OnboardingPage
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
) {
    val pages = OnboardingPage.getOnboardingMockList
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(count = pages.size, state = pagerState) { page ->
        OnboardingPage(onboardingPage = pages[page])
    }
}



@Preview(showBackground = true)
@Composable
private fun OnboardingPreview() {
    OnboardingScreen()
}

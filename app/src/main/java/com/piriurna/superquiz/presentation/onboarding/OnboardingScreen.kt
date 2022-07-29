package com.piriurna.superquiz.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.superquiz.presentation.onboarding.composables.OnboardingCard
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
) {
    val pages = OnboardingPage.getOnboardingMockList
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    HorizontalPager(count = pages.size, state = pagerState) { page ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(pages[page].backgroundColor)
        ) {
            Image(
                painter = painterResource(id = pages[page].mainImage),
                contentDescription = "Page Image",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 36.dp)
                    .padding(top = 36.dp)
            )
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        OnboardingCard(
            onboardingPage = pages[pagerState.currentPage],
            onNextClick = { nextClicked(scope, pagerState) },
            onSkipClick = {
                navigateTo(scope, pagerState, pagerState.pageCount - 1)
            },
            onFinishClick = {
                navigateTo(scope, pagerState, 0)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            selectedPageIndex = pagerState.currentPage,
            pageCount = pages.size
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun nextClicked(scope: CoroutineScope, pagerState: PagerState) {
    val nextPage = min(pagerState.pageCount - 1, pagerState.currentPage + 1)

    navigateTo(scope, pagerState, nextPage);
}

@OptIn(ExperimentalPagerApi::class)
private fun navigateTo(scope: CoroutineScope, pagerState: PagerState, position : Int) {
    scope.launch {
        pagerState.animateScrollToPage(position)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingPreview() {
    OnboardingScreen()
}

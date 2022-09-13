package com.piriurna.superquiz.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.superquiz.presentation.navigation.RootDestinationScreen
import com.piriurna.superquiz.presentation.onboarding.composables.OnboardingCard
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Integer.min

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val viewModel : OnboardingViewModel = hiltViewModel()

    BuildOnboardingScreen(state = viewModel.state.value, navController = navController)
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BuildOnboardingScreen(
    state: OnboardingState,
    navController: NavController,
    asyncImage : Boolean = true
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val pages = state.onboardingPages

    SQScaffold(isLoading = state.isLoading, hasToolbar = false) {
            HorizontalPager(count = pages.size, state = pagerState) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(pages[page].backgroundColor)
                ) {

                    if(asyncImage) {
                        AsyncImage(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(horizontal = 36.dp)
                                .padding(top = 36.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pages[page].mainImageUrl)
                                .fallback(pages[page].mainImage)
                                .error(pages[page].mainImage)
                                .decoderFactory(SvgDecoder.Factory())
                                .crossfade(true)
                                .build(),
                            contentDescription = "Page Image"
                        )
                    } else {
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
            }
        if(pages.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                OnboardingCard(
                    onboardingPage = pages[pagerState.currentPage],
                    onNextClick = { nextClicked(scope, pagerState) },
                    onSkipClick = {
                        navigateTo(scope, pagerState, pagerState.pageCount - 1)
                    },
                    onFinishClick = {
                        navController.navigate(RootDestinationScreen.Home.route)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    selectedPageIndex = pagerState.currentPage,
                    pageCount = pages.size
                )
            }
        }
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
    BuildOnboardingScreen(
        navController = rememberNavController(),
        state = OnboardingState(
            onboardingPages = OnboardingUI.getOnboardingMockList
        ),
        asyncImage = false
    )
}

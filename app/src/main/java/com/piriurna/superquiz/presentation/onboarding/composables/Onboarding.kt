package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPageModel
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingScrollListener
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingViewPagerModel
import com.piriurna.superquiz.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(
    pages : List<OnboardingPageModel>,
    pagerState: PagerState,
    scope : CoroutineScope,
) {

    //Todo: Fix this in order for the model to be more specific and less overpopulated
    pages.forEachIndexed { index, page ->
        page.viewPagerModel = OnboardingViewPagerModel(
            scope = scope,
            state = pagerState
        )

        //TODO: I think it's not the best practice for the page model to have a reference for the total count
        page.pageCount = pages.size

        val isLastPage = index == pages.size - 1
        page.nextPage = if(isLastPage) OnboardingPageModel.FINAL_PAGE_INDICATOR else page.pageIndex + 1
    }

    HorizontalPager(count = pages.size, state = pagerState) { page ->
        OnboardingPage(onboardingPageModel = pages[page])
    }
}



@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun OnboardingPreview() {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val pages = listOf(
        firstPageMock,
        secondPageMock,
        thirdPageMock
    )
    Onboarding(pages, pagerState, scope)
}


val firstPageMock = OnboardingPageModel(
    pageTitle = "Hmmmm, Healthy food",
    pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
    primaryColor = orange,
    backgroundColor = lightPurple,
    pageIndex = 0,
    mainImage = R.drawable.ic_banana_svgrepo_com
)

val secondPageMock = OnboardingPageModel(
    pageTitle = "Fresh Drinks, Stay Fresh",
    pageDescription = "Not only food, we provide clear healthy drink options for you. Fresh taste always accompanies you",
    primaryColor = primaryBlue,
    backgroundColor = backgroundBlue,
    pageIndex = 1,
    mainImage = R.drawable.ic_banana_svgrepo_com
)

val thirdPageMock = OnboardingPageModel(
    pageTitle = "Let's Cooking",
    pageDescription = "are you ready to make a dish for your friends or family? create an account and cook",
    primaryColor = primaryGreen,
    backgroundColor = backgroundGreen,
    pageIndex = 2,
    mainImage = R.drawable.ic_banana_svgrepo_com
)
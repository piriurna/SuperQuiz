package com.piriurna.superquiz.presentation.onboarding.models

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope

data class OnboardingViewPagerModel @OptIn(ExperimentalPagerApi::class) constructor(
    val scope: CoroutineScope,
    val state : PagerState
)

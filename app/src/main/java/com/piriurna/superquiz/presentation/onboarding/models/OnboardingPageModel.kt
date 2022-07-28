package com.piriurna.superquiz.presentation.onboarding.models

import androidx.compose.ui.graphics.Color

data class OnboardingPageModel(
    val primaryColor : Color,
    val backgroundColor : Color,
    val pageIndex : Int,
    var pageCount : Int = 0,
    var nextPage: Int = FINAL_PAGE_INDICATOR,
    val pageTitle : String,
    val pageDescription : String,
    val mainImage : Int = 0,
    var viewPagerModel: OnboardingViewPagerModel? = null
) {

    fun isLastPage() : Boolean {
        return nextPage == FINAL_PAGE_INDICATOR
    }


    companion object {
        //I tried setting it to -1 to be visible but it didn't let me
        const val FINAL_PAGE_INDICATOR = 0
    }
}
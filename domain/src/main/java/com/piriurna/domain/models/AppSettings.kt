package com.piriurna.domain.models

import com.piriurna.domain.models.TimeConstants.TIME_UNTIL_REFRESH
import java.util.*

data class AppSettings(
    val firstInstall : Boolean = true,
    val onboardingComplete : Boolean = false,
    val timeToRefreshCategories : Long = TIME_UNTIL_REFRESH,
    val lastUpdatedCategoriesTimestamp: Long = 0
) {


    fun shouldFetchNewCategories() : Boolean{
        val timePassed = Calendar.getInstance().timeInMillis - lastUpdatedCategoriesTimestamp

        return timePassed >= timeToRefreshCategories
    }
}
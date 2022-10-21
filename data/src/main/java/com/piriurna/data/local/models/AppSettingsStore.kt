package com.piriurna.data.local.models

import com.piriurna.domain.models.TimeConstants
import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsStore(
    val firstInstall : Boolean = true,
    val onboardingComplete : Boolean = false,
    val timeToRefreshCategories : Long = TimeConstants.TIME_UNTIL_REFRESH,
    val lastUpdatedCategoriesTimestamp: Long = 0
)

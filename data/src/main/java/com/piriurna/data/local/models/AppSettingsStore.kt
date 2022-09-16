package com.piriurna.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsStore(
    val firstInstall : Boolean = true,
    val shouldFetchNewCategories : Boolean = false,
    val onboardingComplete : Boolean = false
)

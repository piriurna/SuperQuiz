package com.piriurna.domain.models

data class AppSettings(
    val firstInstall : Boolean = true,
    val shouldFetchNewCategories : Boolean = false, //TODO: calculate when this should be true
    val onboardingComplete : Boolean = false
)
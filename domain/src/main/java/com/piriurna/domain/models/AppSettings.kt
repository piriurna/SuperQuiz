package com.piriurna.domain.models

data class AppSettings(
    val firstInstall : Boolean = true,
    val shouldFetchNewCategories : Boolean = false
)
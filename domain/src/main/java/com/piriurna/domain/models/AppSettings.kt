package com.piriurna.domain.models

data class AppSettings(
    val firstInstall : Boolean = false,
    val shouldFetchNewCategories : Boolean = false
)
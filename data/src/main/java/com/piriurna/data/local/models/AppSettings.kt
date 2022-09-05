package com.piriurna.data.local.models

data class AppSettings(
    val firstInstall : Boolean = false,
    val shouldFetchNewCategories : Boolean = false
)

package com.piriurna.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val firstInstall : Boolean = false,
    val shouldFetchNewCategories : Boolean = false
)

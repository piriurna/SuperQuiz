package com.piriurna.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsStore(
    val firstInstall : Boolean = false,
    val shouldFetchNewCategories : Boolean = false
)

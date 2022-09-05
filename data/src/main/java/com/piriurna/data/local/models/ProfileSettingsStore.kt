package com.piriurna.data.local.models

import kotlinx.serialization.Serializable

@Serializable
data class ProfileSettingsStore(
    val numberOfQuestions : Int = 10
)

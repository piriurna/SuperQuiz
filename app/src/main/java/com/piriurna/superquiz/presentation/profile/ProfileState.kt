package com.piriurna.superquiz.presentation.profile

import com.piriurna.domain.models.ProfileSettings

data class ProfileState(
    val isLoading: Boolean = false,
    val profileSettings : ProfileSettings = ProfileSettings()
)
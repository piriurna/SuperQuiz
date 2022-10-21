package com.piriurna.superquiz.presentation.profile

import com.piriurna.domain.models.ProfileSettings
import com.piriurna.superquiz.presentation.profile.user.UserSettingsActions

data class ProfileSettingsState(
    val isLoading: Boolean = false,
    val profileSettings : ProfileSettings = ProfileSettings(),
    val userSettingsActions: UserSettingsActions = UserSettingsActions.NO_STATE
) {


    fun shouldResetSwipe() = userSettingsActions == UserSettingsActions.RESET_DELETE_SWIPE
}
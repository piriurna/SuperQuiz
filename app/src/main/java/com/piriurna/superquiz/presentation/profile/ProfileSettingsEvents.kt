package com.piriurna.superquiz.presentation.profile

import com.piriurna.domain.models.ProfileSettings
import com.piriurna.superquiz.SQBaseEvent

sealed class ProfileSettingsEvents : SQBaseEvent() {

    object FetchSettings : ProfileSettingsEvents()

    class SaveSettings(val profileSettings: ProfileSettings) : ProfileSettingsEvents()
}
package com.piriurna.superquiz.presentation.profile

import com.piriurna.domain.models.ProfileSettings
import com.piriurna.superquiz.SQBaseEvent

sealed class ProfileEvents : SQBaseEvent() {

    object FetchSettings : ProfileEvents()

    class SaveSettings(profileSettings: ProfileSettings) : ProfileEvents()
}
package com.piriurna.superquiz.presentation.profile

import com.piriurna.domain.models.ProfileSettings
import com.piriurna.superquiz.SQBaseEvent

sealed class ProfileEvents : SQBaseEvent() {

    object FetchSettings : ProfileEvents()

    class SaveSettings(val profileSettings: ProfileSettings) : ProfileEvents()
}
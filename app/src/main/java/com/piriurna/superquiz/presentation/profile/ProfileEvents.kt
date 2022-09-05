package com.piriurna.superquiz.presentation.profile

import com.piriurna.superquiz.SQBaseEvent

sealed class ProfileEvents : SQBaseEvent() {

    object FetchSettings : ProfileEvents()
}
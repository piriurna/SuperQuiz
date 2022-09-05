package com.piriurna.data.local.util

import android.content.Context
import androidx.datastore.dataStore
import com.piriurna.data.local.util.serializer.AppSettingsSerializer
import com.piriurna.data.local.util.serializer.ProfileSettingsSerializer

val Context.appSettingsStore by dataStore(
    fileName = "app-settings.json",
    serializer = AppSettingsSerializer
)


val Context.profileSettingsStore by dataStore(
    fileName = "profile-settings.json",
    serializer = ProfileSettingsSerializer
)

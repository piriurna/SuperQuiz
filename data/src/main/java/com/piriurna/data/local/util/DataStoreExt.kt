package com.piriurna.data.local.util

import android.content.Context
import androidx.datastore.dataStore
import com.piriurna.data.local.util.serializer.AppSettingsSerializer

val Context.appSettingsStore by dataStore(
    fileName = "app-settings.json",
    serializer = AppSettingsSerializer
)

package com.piriurna.data.mappers

import com.piriurna.data.local.models.AppSettingsStore
import com.piriurna.domain.models.AppSettings

fun AppSettingsStore.toAppSettings() : AppSettings {
    return AppSettings(
        firstInstall = this.firstInstall,
        shouldFetchNewCategories = this.shouldFetchNewCategories
    )
}


fun AppSettings.toAppSettingsStore() : AppSettingsStore {
    return AppSettingsStore(
        firstInstall = this.firstInstall,
        shouldFetchNewCategories = this.shouldFetchNewCategories
    )
}
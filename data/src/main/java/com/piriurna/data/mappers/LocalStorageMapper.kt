package com.piriurna.data.mappers

import com.piriurna.data.local.models.AppSettingsStore
import com.piriurna.data.local.models.ProfileSettingsStore
import com.piriurna.domain.models.AppSettings
import com.piriurna.domain.models.ProfileSettings

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


fun ProfileSettingsStore.toProfileSettings() : ProfileSettings {
    return ProfileSettings(
        numberOfQuestions = this.numberOfQuestions,
        userName = this.userName
    )
}

fun ProfileSettings.toProfileSettingsStore() : ProfileSettingsStore {
    return ProfileSettingsStore(
        numberOfQuestions = this.numberOfQuestions,
        userName = this.userName
    )
}
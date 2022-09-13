package com.piriurna.common.composables.toolbar

import androidx.compose.runtime.Composable

interface OptionsAppbar {

    fun getOptionsButton() : @Composable () -> Unit
}
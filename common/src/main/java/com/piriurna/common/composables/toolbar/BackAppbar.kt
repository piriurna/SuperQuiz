package com.piriurna.common.composables.toolbar

import androidx.compose.runtime.Composable

interface BackAppbar {

    fun getBackButton() : @Composable () -> Unit
}
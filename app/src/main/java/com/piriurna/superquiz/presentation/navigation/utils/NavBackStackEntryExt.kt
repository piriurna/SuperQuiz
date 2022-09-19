package com.piriurna.superquiz.presentation.navigation.utils

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.getArgument(argument: String) : String? {
    return this.arguments?.getString(argument)
}
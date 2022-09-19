package com.piriurna.common.composables.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

abstract class BaseDestinations(
    private val route: String,
    private val customArguments: List<CustomArguments> = emptyList()
) {
    val fullRoute: String = buildString {
        append(route)
        customArguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
            append("$symbol${custom.key}={${custom.key}}")
        }
    }

    val arguments: List<NamedNavArgument> = customArguments.map {
        navArgument(it.key) {
            type = it.type
            nullable = it.nullable
        }
    }

    fun withArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${customArguments[index].key}=$arg")
            }
        }
    }
}

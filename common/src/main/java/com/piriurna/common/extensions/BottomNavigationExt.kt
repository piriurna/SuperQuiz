package com.piriurna.common.extensions

import com.piriurna.common.models.BottomNavigationItem

fun List<BottomNavigationItem>.getSelected(route: String?) : BottomNavigationItem? {
    return this.firstOrNull { it.route == route }
}
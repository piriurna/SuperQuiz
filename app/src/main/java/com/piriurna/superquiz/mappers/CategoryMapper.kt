package com.piriurna.superquiz.mappers

import com.piriurna.common.models.SelectableItem
import com.piriurna.domain.models.Category

fun Category.getImage() : Int {
    return CategoryImage.getCategoryById(this.id).imageRes;
}


fun Category.toSelectableItem() : SelectableItem {
    return SelectableItem(
        id = this.id.toString(),
        name = this.name
    )
}

fun List<Category>.toSelectableItem() : List<SelectableItem> {
    return this.map {
        it.toSelectableItem()
    }
}
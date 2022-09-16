package com.piriurna.superquiz.mappers

import com.piriurna.common.models.SelectableItem
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.CategoryStatistics

fun Category.getImage() : Int {
    return CategoryImage.getCategoryById(this.id).imageRes;
}


fun CategoryStatistics.toSelectableItem() : SelectableItem {
    return SelectableItem(
        id = this.categoryId.toString(),
        name = this.categoryId.toString()
    )
}

fun List<CategoryStatistics>.toSelectableItem() : List<SelectableItem> {
    return this.map {
        it.toSelectableItem()
    }
}
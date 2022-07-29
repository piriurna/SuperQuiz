package com.piriurna.superquiz.mappers

import com.piriurna.domain.models.Category

fun Category.getImage() : Int {
    return CategoryImage.getCategoryById(this.id).imageRes;
}
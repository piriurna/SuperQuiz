package com.piriurna.data.mappers

import com.piriurna.data.remote.dto.CategoryDto
import com.piriurna.domain.models.Category

fun CategoryDto.toCategory() : List<Category> {
    return this.triviaCategories.map { categoryDto ->
        return@map Category(
            id = categoryDto.id,
            name = categoryDto.name
        )
    }
}
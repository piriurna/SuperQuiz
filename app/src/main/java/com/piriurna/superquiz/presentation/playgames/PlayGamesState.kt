package com.piriurna.superquiz.presentation.playgames

import com.piriurna.common.models.SQError
import com.piriurna.domain.models.Category

data class PlayGamesState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val userName : String = "",
    val isRefreshing : Boolean = false,
    val error : SQError? = null
)

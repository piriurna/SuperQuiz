package com.piriurna.domain.models

data class Category(
    val id: Int,
    val name: String,
    val completionRate : Int = 0 //0-100 in percentage
)

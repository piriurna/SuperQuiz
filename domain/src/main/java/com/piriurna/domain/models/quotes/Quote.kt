package com.piriurna.domain.models.quotes

data class Quote(
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val id: String,
    val length: Int,
    val tags: List<String>
) {

    companion object {
        val mockQuote = Quote(
            id = "0",
            tags = emptyList(),
            length = 24,
            authorSlug = "franco-zalamena",
            content = "Quote teste para mostrar",
            author = "Franco Zalamena",
            dateAdded = "",
            dateModified = ""
        )
    }
}
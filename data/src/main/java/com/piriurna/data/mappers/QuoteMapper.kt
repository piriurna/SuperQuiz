package com.piriurna.data.mappers

import com.piriurna.data.remote.dto.quotes.QuoteDto
import com.piriurna.data.remote.dto.quotes.QuoteListDto
import com.piriurna.domain.models.quotes.Quote

fun QuoteDto.toQuote() : Quote {
    return Quote(
        id = this.id,
        author = this.author,
        authorSlug = this.authorSlug,
        content = this.content,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified,
        length = this.length,
        tags = this.tags
    )
}




fun Quote.toQuoteDto() : QuoteDto {
    return QuoteDto(
        id = this.id,
        author = this.author,
        authorSlug = this.authorSlug,
        content = this.content,
        dateAdded = this.dateAdded,
        dateModified = this.dateModified,
        length = this.length,
        tags = this.tags
    )
}


fun QuoteListDto.toQuote() : List<Quote> {
    return this.results.map {
        it.toQuote()
    }
}
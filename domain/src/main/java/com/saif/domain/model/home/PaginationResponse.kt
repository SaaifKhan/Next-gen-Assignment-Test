package com.saif.domain.model.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationResponse<T>(
    val page: Int?,
    val results: List<T>? = listOf(),
    val total_pages: Int?,
    val total_results: Int?
)

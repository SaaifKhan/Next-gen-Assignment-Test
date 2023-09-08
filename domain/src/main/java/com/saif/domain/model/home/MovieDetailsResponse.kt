package com.saif.domain.model.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    val backdrop_path: String?="",
    val genres: List<Genre>?= emptyList(),
    val id: Int? = -1,
    val original_language: String? = "",
    val overview: String? = "",
    val poster_path: String? = "",
    val production_companies: List<ProductionCompany>? = emptyList(),
    val release_date: String? = "",
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val title: String? = "",
) {
    @JsonClass(generateAdapter = true)
    data class Genre(
        val id: Int? = -1,
        val name: String? = ""
    )
    @JsonClass(generateAdapter = true)
    data class ProductionCompany(
        val id: Int? = -1,
        val logo_path: String? = "",
        val name: String? = "",
        val origin_country: String? = ""
    )
    @JsonClass(generateAdapter = true)
    data class SpokenLanguage(
        val english_name: String? = "",
        val iso_639_1: String? = "",
        val name: String? = ""
    )

}
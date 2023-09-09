package com.saif.domain.model.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    var backdrop_path: String?="",
    var genres: List<Genre>?= emptyList(),
    var id: Int? = -1,
    var original_language: String? = "",
    var overview: String? = "",
    var poster_path: String? = "",
    var production_companies: List<ProductionCompany>? = emptyList(),
    var release_date: String? = "",
    var spoken_languages: List<SpokenLanguage>? = emptyList(),
    var title: String? = "",
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
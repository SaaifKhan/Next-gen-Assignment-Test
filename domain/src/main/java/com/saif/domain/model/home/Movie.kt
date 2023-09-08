package com.saif.domain.model.home

import androidx.databinding.Bindable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int?>? = emptyList(),
    val id: Int?= -1,
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = -1
){
    fun getGenreSize() : String{
        var genre = ""
        genre = genre_ids?.size.toString()
        return genre
    }
}

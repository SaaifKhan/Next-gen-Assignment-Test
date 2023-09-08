package com.saif.data.network

import com.saif.data.BuildConfig

object API {
    private const val API = BuildConfig.BASE_URL
    private const val VERSION = BuildConfig.VERSION
    const val MOVIES_LIST = "$API$VERSION/movie/popular"
    const val MOVIE_DETAILS = "$API$VERSION/movie/{movie_id}"
}
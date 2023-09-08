package com.saif.data.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

inline fun <reified T> Moshi.fromJson(json: String, type: Type): T? {
    val jsonAdapter : JsonAdapter<T> = adapter(type)
    return jsonAdapter.fromJson(json)
}
package com.saif.data.network

import com.saif.data.util.Applog
import com.saif.data.util.TypeReference
import com.saif.data.util.fromJson
import com.saif.domain.exception.ServerException
import com.saif.domain.exception.UnProcessableEntityException
import com.saif.domain.exception._404Exception
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkCall @Inject constructor(
    var apiInterface: ApiInterface,
    var moshi: Moshi
) {

    suspend inline fun <reified T> generalRequest(
        crossinline request: suspend () -> Response<ResponseBody>
    ): Flow<T?> =
        flow {
            val response = request()
            if (response.isSuccessful) {
                val responseString = response.body()?.string()
                val type = object : TypeReference<T>() {}.type
                val model = moshi.fromJson<T>(responseString ?: "", type) as T?
                emit(model)
            } else {
                when (response.code()) {
                    422 -> {
                        val errorResponse = response.errorBody()?.string() ?: "{}"
                        throw UnProcessableEntityException(handle422Error(errorResponse))
                        Applog.d("Error Response::", errorResponse.toString())
                    }

                    500 -> {
                        var errorResponse = response.errorBody()?.string() ?: "{}"
                        Applog.d("Error Response::", errorResponse.toString())
                        throw ServerException()
                    }

                    400 -> {
                        var errorResponse = response.errorBody()?.string() ?: "{}"
                        Applog.d("Error Response::", errorResponse.toString())
                        throw _404Exception()
                    }

                    else -> {
                        var errorResponse = response.errorBody()?.string() ?: "{}"
                        Applog.d("Error Response::", errorResponse.toString())
                        throw Exception(errorResponse)
                    }
                }
            }
        }

    @Throws(Exception::class)
    suspend inline fun <reified T> get(
        endpoint: String,
        queryMap: Map<String, String>? //query params
    ): Flow<T?> {

        return if (queryMap == null)
            generalRequest<T> { apiInterface.get(endpoint) }
        else
            generalRequest<T> { apiInterface.get(endpoint, queryMap) }
    }

    fun handle422Error(
        errorResponse: String
    ): HashMap<String, String> {
        val errorMap = HashMap<String, String>()

        var err: JSONObject = JSONObject(errorResponse)
        if (err.has("errors")) {
            err = err.getJSONObject("errors")
        }
        if (err.has("error")) {
            err = err.getJSONObject("error")
        }

        val keys = err.keys()
        while (keys.hasNext()) {
            var dynamicKey = keys.next().toString()
            try {
                val jsonArray = err.getJSONArray(dynamicKey)
                if (jsonArray.length() > 0) {
                    errorMap.put(dynamicKey, jsonArray.getString(0))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return errorMap
    }


}
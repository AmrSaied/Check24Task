package com.check24.data.network

import android.content.Context
import com.check24.data.R
import retrofit2.Response

/**
 * Network operation executor for retrofit library
 */
open class RetrofitExecutor(private val context: Context) {


    suspend fun <T : Any> makeRequest(
        call: suspend () -> Response<T>
    ): T {
        return safeApiResult(call)
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): T {
        val result = call()
        return when (result.isSuccessful) {
            false -> throw Exception(context.getString(R.string.something_went_wrong))
            else -> result.body()!!
        }
    }
}
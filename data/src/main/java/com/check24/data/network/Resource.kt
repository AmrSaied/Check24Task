package com.check24.data.network

/**
 * State management class for ui states providing [T] as the success state data type
 */
sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T?) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
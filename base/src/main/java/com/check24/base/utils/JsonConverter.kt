package com.check24.base.utils

import android.content.Context
import com.google.gson.Gson


inline fun <reified T> toJsonArray(model: List<T>?): String? = Gson().toJson(model,object : com.google.gson.reflect.TypeToken<List<T?>?>() {}.type)

inline fun <reified T> fromJsonArray(jsonString: String?): List<T>? = Gson().fromJson<List<T>>(jsonString, object : com.google.gson.reflect.TypeToken<List<T?>?>() {}.type)

inline fun <reified T> toJson(model: T?): String? = Gson().toJson(model,T::class.java)

inline fun <reified T> fromJson(jsonString: String?): T? = Gson().fromJson<T>(jsonString,T::class.java)


inline fun <reified T>jsonAssetFileToObj(context : Context,assetFilePath: String): T? {
    val jsonString= context.assets.open(assetFilePath).bufferedReader().use { it.readText() }
    return fromJson(jsonString)
}

inline fun <reified T>jsonAssetFileToArr(context : Context,assetFilePath: String): List<T>? {
    val jsonString= context.assets.open(assetFilePath).bufferedReader().use { it.readText() }
    return fromJsonArray(jsonString)
}

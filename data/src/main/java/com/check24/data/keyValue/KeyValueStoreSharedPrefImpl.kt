package com.check24.data.keyValue

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * KeyValueStore implementation with shared preference
 */
class KeyValueStoreSharedPrefImpl(private val pref:SharedPreferences) : KeyValueStore {

    override fun toggleFavProduct(id: Int, fav: Boolean) {
        pref.edit().putBoolean(getKeyName(id),fav).apply()
    }

    override suspend fun isFavProduct(id: Int): Boolean {
        return withContext(Dispatchers.IO){pref.getBoolean(getKeyName(id),false)}
    }

    companion object {
        const val KEY_FAV_PRODUCT= "is_fav_product"
    }


    private fun getKeyName(id:Int) = "${KEY_FAV_PRODUCT}_$id"

}
package com.check24.data.keyValue



/**
 * KeyValueStore contract
 */
interface KeyValueStore {

     fun toggleFavProduct(id: Int, fav: Boolean)

    suspend fun isFavProduct(id:Int):Boolean




}
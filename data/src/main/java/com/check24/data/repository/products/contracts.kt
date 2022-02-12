package com.check24.data.repository.products

import com.check24.data.network.entities.productsListDto
import com.check24.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface ProductsRemoteDataSource {

    suspend fun getproductsList(): productsListDto

}


interface ProductsRepo {

    fun getproductsListFlow(): Flow<Resource<productsListDto>>

    fun toggleProductFav(id:Int,fav: Boolean)

    fun isProductFav(id: Int) : Flow<Resource<Boolean>>

}
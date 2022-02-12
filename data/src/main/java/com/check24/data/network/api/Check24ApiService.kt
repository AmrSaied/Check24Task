package com.check24.data.network.api


import com.check24.data.constant.PRODUCTS_OVERVIEW_API
import com.check24.data.network.entities.productsListDto
import retrofit2.Response
import retrofit2.http.GET

interface Check24ApiService {

    @GET(PRODUCTS_OVERVIEW_API)
    suspend fun getProductsList(): Response<productsListDto>



}
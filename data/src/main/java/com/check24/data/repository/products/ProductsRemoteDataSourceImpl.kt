package com.check24.data.repository.products

import com.check24.data.network.RetrofitExecutor
import com.check24.data.network.api.Check24ApiService
import com.check24.data.network.entities.productsListDto


class ProductsRemoteDataSourceImpl(private val retrofitExecutor: RetrofitExecutor, private val apiService: Check24ApiService) :
    ProductsRemoteDataSource {


    override suspend fun getproductsList(): productsListDto {
       return retrofitExecutor.makeRequest { apiService.getProductsList() }
    }


}
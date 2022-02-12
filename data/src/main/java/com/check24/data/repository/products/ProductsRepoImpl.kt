package com.check24.data.repository.products

import android.content.Context
import com.check24.base.utils.NetworkHelper
import com.check24.data.keyValue.KeyValueStore
import com.check24.data.network.Resource
import com.check24.data.network.entities.productsListDto
import com.check24.data.repository.RemoteLocalBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class ProductsRepoImpl( private val context:Context , private val networkHelper: NetworkHelper,
                        private val keyValueStore: KeyValueStore,
                        private val remoteDataSource: ProductsRemoteDataSource)  :ProductsRepo {

    override fun getproductsListFlow(): Flow<Resource<productsListDto>> {
        return RemoteLocalBoundResource(context,networkHelper, remoteCall = {remoteDataSource.getproductsList()}).asFlow()
    }

    override fun toggleProductFav(id: Int,fav: Boolean) {
        keyValueStore.toggleFavProduct(id,fav)
    }

    override fun isProductFav(id: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(Resource.Success(keyValueStore.isFavProduct(id)))
        }
    }


}
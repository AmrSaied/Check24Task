package com.check24.data.repository

import android.content.Context
import androidx.annotation.MainThread
import com.check24.base.utils.NetworkHelper
import com.check24.data.R
import com.check24.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


class RemoteLocalBoundResource<ResultType : Any>
@MainThread constructor(
    private val context:Context,
    private var networkHelper: NetworkHelper,
    private val remoteCall: suspend () -> ResultType,

    ) {

    fun asFlow(): Flow<Resource<ResultType>> {
        return flow {
            emit(Resource.Loading)

            try {

                if (networkHelper.isConnected()) {
                    emit(Resource.Success(withContext(Dispatchers.IO){remoteCall()}))
                }else{
                    emit(Resource.Error(Exception(context.getString(R.string.no_internet_connection))))
                }

            }catch (e:Exception){
                emit(Resource.Error(e))
            }

        }
    }


}
package com.check24.task.repo

import android.content.Context
import com.check24.base.utils.NetworkHelper
import com.check24.data.keyValue.KeyValueStore
import com.check24.data.network.Resource
import com.check24.data.network.entities.productsListDto
import com.check24.data.repository.products.ProductsRemoteDataSource
import com.check24.data.repository.products.ProductsRepo
import com.check24.data.repository.products.ProductsRepoImpl
import com.check24.task.R
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ProductsRepoImplTest{

    private lateinit var productsRepo: ProductsRepo

    @Mock
    private lateinit var remoteDataSource: ProductsRemoteDataSource
    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var keyValueStore: KeyValueStore
    @Mock
    private lateinit var  networkHelper: NetworkHelper



    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        productsRepo = ProductsRepoImpl(context, networkHelper, keyValueStore,remoteDataSource)
    }



    @Test
    fun `test Favorite`(): Unit = runBlocking {
        val result = Mockito.mock(productsListDto::class.java)
        Mockito.`when`(remoteDataSource.getproductsList())
            .thenReturn(result)

        val allItems = productsRepo.getproductsListFlow().toList()
        var objectResult = (allItems[1] as Resource.Success).data shouldBeEqualTo result;
        productsRepo.toggleProductFav(objectResult!!.products[0].id, true)

        val isFav =  productsRepo.isProductFav(objectResult.products[0].id)
        var favResult = (isFav as Resource.Success<*>).data shouldBeEqualTo result;

        Assert.assertEquals(favResult ,true)
    }


    @Test
    fun `test GetProductsList When Network Available`(): Unit = runBlocking {

        val result = Mockito.mock(productsListDto::class.java)

        Mockito.`when`(networkHelper.isConnected())
            .thenReturn(true)

        Mockito.`when`(remoteDataSource.getproductsList())
            .thenReturn(result)

        val allItems = productsRepo.getproductsListFlow().toList()

        Assert.assertEquals(2,allItems.size)

        allItems[0] shouldBeInstanceOf Resource.Loading::class.java
        allItems[1] shouldBeInstanceOf Resource.Success::class.java

        (allItems[1] as Resource.Success).data shouldBeEqualTo result

        Mockito.verify(remoteDataSource, Mockito.times(1))
            .getproductsList()

    }


    @Test
    fun `test GetProductsList Network Not Available`(): Unit = runBlocking {

        val error = "No internet connection"

        Mockito.`when`(context.getString(R.string.no_internet_connection))
            .thenReturn(error)

        Mockito.`when`(networkHelper.isConnected())
            .thenReturn(false)


        val allItems = productsRepo.getproductsListFlow().toList()

        Assert.assertEquals(allItems.size,2)

        allItems[0] shouldBeInstanceOf Resource.Loading::class.java
        allItems[1] shouldBeInstanceOf Resource.Error::class.java
        ( allItems[1] as Resource.Error ).exception.message shouldBeEqualTo error

        Mockito.verify(remoteDataSource, Mockito.never())
            .getproductsList()

    }


}
package com.check24.data.di



import com.check24.data.repository.products.ProductsRemoteDataSource
import com.check24.data.repository.products.ProductsRemoteDataSourceImpl
import com.check24.data.repository.products.ProductsRepo
import com.check24.data.repository.products.ProductsRepoImpl
import org.koin.dsl.module


/**
 * Koin module for repositories , remote/local data sources an other sources
 */
val  repositoryModule = module {

    factory<ProductsRemoteDataSource>{ ProductsRemoteDataSourceImpl(get(),get()) }

    factory<ProductsRepo>{ ProductsRepoImpl(get(),get(),get(),get()) }


}
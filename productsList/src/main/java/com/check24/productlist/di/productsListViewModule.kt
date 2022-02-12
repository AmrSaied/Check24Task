package com.check24.productlist.di


import com.check24.productlist.domian.GetproductsListUseCase
import com.check24.productlist.viewModel.productsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val productsListModule = module {

    factory { GetproductsListUseCase(get()) }

    viewModel {productsListViewModel(androidApplication(),get())}
}
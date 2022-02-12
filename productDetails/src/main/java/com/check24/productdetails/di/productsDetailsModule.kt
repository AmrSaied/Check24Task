package com.check24.productdetails.di


import com.check24.data.network.entities.ProductsItem
import com.check24.productdetails.domain.IsProductFavUseCase
import com.check24.productdetails.domain.ToggleFavProductUseCase
import com.check24.productdetails.view.ProductDetailsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val productsDetailsModule = module {

    factory { IsProductFavUseCase(get()) }

    factory { ToggleFavProductUseCase(get()) }

    viewModel {(model: ProductsItem) -> ProductDetailsViewModel(model,androidApplication(),get(),get())}
}
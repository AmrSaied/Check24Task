package com.check24.productlist.viewModel

import com.check24.data.network.entities.ProductsItem


data class ProductsItemUiModel(

    val releaseDate: String,

    val priceTxt: String,

    val imageURL: String,

    val name: String,

    val available: Boolean,

    val rating: Float,

    val description: String,

    val id: Int,

    val dto: ProductsItem,

    )
package com.check24.productlist.viewModel


class ProductListUiModel(
    val headerDescription: String,

    val headerTitle: String,

    val filters: List<String>,

    val products: List<ProductsItemUiModel>
)

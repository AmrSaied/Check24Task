package com.check24.productlist.viewModel


sealed class ProductsListUiState {
    data class Success(val data: ProductListUiModel) : ProductsListUiState()
    data class Error(val errorMsg: String) : ProductsListUiState()
    object Loading : ProductsListUiState()
}
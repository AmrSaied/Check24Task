package com.check24.productdetails.view


sealed class ProductDetailsUiState {
    data class Success(val data: ProductDetailsUiModel) : ProductDetailsUiState()
    data class Error(val errorMsg: String) : ProductDetailsUiState()
    object Loading : ProductDetailsUiState()
}
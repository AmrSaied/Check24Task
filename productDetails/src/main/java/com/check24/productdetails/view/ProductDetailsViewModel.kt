package com.check24.productdetails.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.check24.data.network.Resource
import com.check24.data.network.entities.ProductsItem
import com.check24.productdetails.R
import com.check24.productdetails.domain.IsProductFavUseCase
import com.check24.productdetails.domain.ToggleFavProductUseCase
import com.check24.productdetails.mapper.toProductsDetailsUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val model : ProductsItem ,private val context: Application, private val isProductFavUseCase: IsProductFavUseCase ,
                              private val favProductUseCase: ToggleFavProductUseCase):ViewModel(){



    private val _uiState = MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            isProductFavUseCase(model.id)
                .map {
                    when(it){
                        is Resource.Success ->{
                            ProductDetailsUiState.Success(toProductsDetailsUiModel(model,it.data!!))
                        }
                        is Resource.Loading -> ProductDetailsUiState.Loading
                        is Resource.Error -> ProductDetailsUiState.Error(it.exception.message?:context.getString(
                            R.string.something_went_wrong))
                    }
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

    fun toggleFavStatus() {
        val isFav = ( uiState.value as ProductDetailsUiState.Success).data.isFav
        favProductUseCase(model.id,!isFav)
        fetchData()
    }


}
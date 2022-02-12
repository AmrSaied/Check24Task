package com.check24.productlist.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.check24.data.network.Resource
import com.check24.data.network.entities.productsListDto
import com.check24.productlist.R
import com.check24.productlist.domian.GetproductsListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class productsListViewModel(
    private val context: Application,
    private val productsListUseCase: GetproductsListUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<ProductsListUiState>(ProductsListUiState.Loading)
    val uiState = _uiState.asStateFlow()


    var products: MutableLiveData<List<ProductsItemUiModel>> = MutableLiveData();

    init {
        fetchData()
    }


    fun filterData(data: String): List<ProductsItemUiModel>? {


         return  products.value?.filter {

            if (data == "Alle") {
                it.available == true || it.available == false
            } else if (data == "Verfügbar") {
                it.available == true

            } else if (data == "Vorgemerkt") {
                it.available == false

            } else {
                it.available == true || it.available == false
            }

        }
     }


    fun refresh() {
        _uiState.value = ProductsListUiState.Loading;
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            productsListUseCase()
                .map {
                    when (it) {
                        is Resource.Success -> {
                            ProductsListUiState.Success(toproductsListUiModel(it.data!!))
                        }
                        is Resource.Loading -> ProductsListUiState.Loading
                        is Resource.Error -> ProductsListUiState.Error(
                            it.exception.message ?: context.getString(R.string.something_went_wrong)
                        )
                    }
                }
                .collect {
                    _uiState.value = it
                }
        }
    }


    private fun toproductsListUiModel(dto: productsListDto): ProductListUiModel {

        var uiModel = ProductListUiModel(
            headerDescription = dto.header.headerDescription,
            headerTitle = dto.header.headerTitle,
            filters = dto.filters,
            products = dto.products.map {

                val spf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())


                val releaseDate = spf.format(Date(TimeUnit.SECONDS.toMillis(it.releaseDate)))

                val price = "${it.price.value} ${it.price.currency}"
                ProductsItemUiModel(
                    releaseDate = releaseDate,
                    priceTxt = price,
                    imageURL = it.imageURL,
                    name = it.name,
                    available = it.available,
                    rating = it.rating,
                    description = it.description,
                    id = it.id,
                    dto = it
                )
            }
        )

        products.postValue(uiModel.products);
        return uiModel;
    }


}
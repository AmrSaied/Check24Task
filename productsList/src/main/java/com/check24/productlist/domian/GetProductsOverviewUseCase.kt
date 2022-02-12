package com.check24.productlist.domian

import com.check24.data.repository.products.ProductsRepo


open class GetproductsListUseCase constructor(private val repository : ProductsRepo) {

    operator fun invoke() = repository.getproductsListFlow()

}
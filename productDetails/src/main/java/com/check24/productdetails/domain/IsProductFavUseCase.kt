package com.check24.productdetails.domain

import com.check24.data.repository.products.ProductsRepo


class IsProductFavUseCase constructor(private val repository : ProductsRepo) {

    operator fun invoke(id:Int) = repository.isProductFav(id)

}
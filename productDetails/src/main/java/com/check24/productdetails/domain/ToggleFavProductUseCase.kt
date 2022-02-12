package com.check24.productdetails.domain

import com.check24.data.repository.products.ProductsRepo


class ToggleFavProductUseCase constructor(private val repository : ProductsRepo) {

    operator fun invoke(id:Int,isFav:Boolean) = repository.toggleProductFav(id,isFav)

}
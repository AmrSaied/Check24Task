package com.check24.productdetails.mapper

import com.check24.data.network.entities.ProductsItem
import com.check24.productdetails.view.ProductDetailsUiModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun toProductsDetailsUiModel(dto:ProductsItem , isFav:Boolean):ProductDetailsUiModel{

    val spf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val releaseDate = spf.format(Date(TimeUnit.SECONDS.toMillis(dto.releaseDate)))

    val price = "${dto.price.value} ${dto.price.currency}"


    return ProductDetailsUiModel(
        releaseDate = releaseDate,
        priceTxt = price,
        imageURL = dto.imageURL,
        name = dto.name,
        rating = dto.rating,
        description = dto.description,
        id = dto.id,
        isFav = isFav,
        longDescription = dto.longDescription
    )


}


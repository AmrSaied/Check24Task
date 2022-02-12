package com.check24.data.network.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

open class productsListDto(

	@field:SerializedName("header")
	val header: Header,

	@field:SerializedName("filters")
	val filters: List<String>,

	@field:SerializedName("products")
	val products: List<ProductsItem>
)

@Parcelize
data class Price(

	@field:SerializedName("currency")
	val currency: String,

	@field:SerializedName("value")
	val value: Double
):Parcelable

data class Header(

	@field:SerializedName("headerDescription")
	val headerDescription: String,

	@field:SerializedName("headerTitle")
	val headerTitle: String
)

@Parcelize
data class ProductsItem(

	@field:SerializedName("longDescription")
	val longDescription: String,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("releaseDate")
	val releaseDate: Long,

	@field:SerializedName("price")
	val price: Price,

	@field:SerializedName("imageURL")
	val imageURL: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("available")
	val available: Boolean,

	@field:SerializedName("rating")
	val rating: Float,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("colorCode")
	val colorCode: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String
): Parcelable

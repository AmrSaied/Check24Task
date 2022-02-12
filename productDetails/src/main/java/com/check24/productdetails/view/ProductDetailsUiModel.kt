package com.check24.productdetails.view

import android.content.Context
import android.graphics.Color
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.check24.productdetails.R
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProductDetailsUiModel(

    val longDescription: String,

    val releaseDate: String,

    val priceTxt: String,

    val imageURL: String,

    val name: String,

    val isFav: Boolean,

    val rating: Float,

    val description: String,

    val id: Int,

    ):Parcelable {

    fun getNameColor(context: Context) = ContextCompat.getColor(context,if (isFav) R.color.colorPrimary else R.color.colorSecondaryText)

    fun getFavBtnText(context: Context) = context.getString(if (isFav) R.string.un_favourite else R.string.favourite)

}
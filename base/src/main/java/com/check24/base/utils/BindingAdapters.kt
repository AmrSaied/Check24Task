package com.check24.base.utils

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.check24.base.R


@BindingAdapter("app:image")
fun bindImage(view: ImageView, url: String?) {

    view.load(url) {
        error(R.drawable.stf_ic_error)
            .placeholder(R.drawable.loading)

    }
}


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}
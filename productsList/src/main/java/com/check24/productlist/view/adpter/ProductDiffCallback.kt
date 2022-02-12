package com.check24.productlist.view.adpter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.check24.productlist.viewModel.ProductsItemUiModel

/**
 * A generic diff callback that checks if items are the same by default equal implementation
 */
open class ProductDiffCallback : DiffUtil.ItemCallback<ProductsItemUiModel>() {
    override fun areItemsTheSame(oldItem: ProductsItemUiModel, newItem: ProductsItemUiModel): Boolean {
        return oldItem.id == newItem.id;
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ProductsItemUiModel, newItem: ProductsItemUiModel): Boolean {
        return oldItem.available == newItem.available;

    }
}
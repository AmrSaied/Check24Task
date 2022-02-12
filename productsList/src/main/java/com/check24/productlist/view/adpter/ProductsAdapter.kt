package com.check24.productlist.view.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.check24.base.baseEntities.BaseAdapter
import com.check24.productlist.databinding.ItemProductBinding
import com.check24.productlist.viewModel.ProductsItemUiModel

class ProductsAdapter(private val onItemClick : (ProductsItemUiModel)->Unit):
    BaseAdapter<ProductsItemUiModel>(callback = ProductDiffCallback()) {


    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        val item = getItem(position)
        (binding as ItemProductBinding).apply {
            model = item
            onClick = View.OnClickListener {
                onItemClick(item)
            }
        }
    }
}
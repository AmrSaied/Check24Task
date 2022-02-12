package com.check24.productdetails.navigation

import android.os.Bundle
import com.check24.base.baseEntities.BaseNavigationManager
import com.check24.data.network.entities.ProductsItem
import com.check24.productdetails.view.ProductDetailsActivity

class ProductDetailsNavigation : BaseNavigationManager() {

    fun startDetailsScreen(model: ProductsItem){
        startWithBundle(ProductDetailsActivity::class.java, Bundle().apply {
            putParcelable(KEY_OBJECT,model)
        })
    }


}
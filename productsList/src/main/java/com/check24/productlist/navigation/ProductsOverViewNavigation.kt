package com.check24.productlist.navigation

import com.check24.base.baseEntities.BaseNavigationManager
import com.check24.productlist.view.productsListActivity

class productsListNavigation : BaseNavigationManager() {

    fun startOverViewScreen(){
        start(productsListActivity::class.java)
    }



}